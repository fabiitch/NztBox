package com.nzt.box.test.screens.w2d.collisions.rebond;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.shapes.utils.CircleUtils;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class ST2BodyCollisionReboundCircles extends BaseST2BodyCollisionRebound<CircleShape, CircleShape> {


    Vector2 tangent = new Vector2();

    public ST2BodyCollisionReboundCircles(FastTesterMain main) {
        super(main);
    }

    Vector2 posOnStatic = new Vector2();
    Vector2 normalTangent = new Vector2();
    Vector2 reflexion = new Vector2(1, 0);
    Vector2 velocityAtImpact = new Vector2(1, 0);

    @Override
    protected void doBeginContact() {
        velocityAtImpact = bodyMove.getVelocity(velocityAtImpact);

        Circle circleMove = (Circle) bodyMove.fixtures.get(0).bodyShape.shape;
        Circle circleStatic = (Circle) bodyStatic.fixtures.get(0).bodyShape.shape;

        Vector2 half = V2.middle(circleMove.x, circleMove.y, circleStatic.x, circleStatic.y, new Vector2());
        Vector2 dir = V2.directionTo(circleStatic.x, circleStatic.y, circleMove.x, circleMove.y, new Vector2());
        posOnStatic = CircleUtils.posWithAngleDeg(circleStatic, dir.angleDeg(), posOnStatic);

        tangent = CircleUtils.getTangent(circleStatic, half, new Vector2());
        normalTangent = CircleUtils.dirFromCenter(circleStatic, half, normalTangent);

        float angleIncidence = AngleUtils.angleIncidence(tangent, bodyMove.getVelocity(new Vector2()));
        float angleReflexion = AngleUtils.incidenceToReflexion(angleIncidence);

        reflexion.setAngleDeg(angleReflexion);
    }


    @Override
    public void renderContactInfo(float dt) {
        if (drawContactInfo) {
            Vector2 posBodyMove = bodyMove.getPosition(new Vector2());
            shapeRenderer.setColor(Color.GRAY);
            shapeRenderer.line(posBodyMove, posBodyMove.cpy().add(velocityAtImpact.setLength(500)));

            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.line(posOnStatic.cpy().add(tangent.setLength(150)), posOnStatic.cpy().sub(tangent.setLength(150)));
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.line(posOnStatic, posOnStatic.cpy().add(normalTangent.setLength(100)));
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(posOnStatic, posOnStatic.cpy().sub(velocityAtImpact.setLength(150)));
            shapeRenderer.setColor(Color.PURPLE);
            shapeRenderer.line(posOnStatic, posOnStatic.cpy().add(reflexion.setLength(150)));
        }
    }

    @Override
    protected CircleShape createBodyShape1() {
        return createCircle(50);
    }

    @Override
    protected CircleShape createBodyShape2() {
        return createCircle(100);
    }
}
