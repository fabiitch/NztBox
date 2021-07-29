package com.nzt.box.test.screens.collisions.rebond;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.screens.collisions.BaseSTCollision;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.rebound")
public abstract class BaseSTCollisionRebound<S1 extends BodyShape, S2 extends BodyShape> extends BaseSTCollision<S1, S2> {

    private Vector2 target = new Vector2();

    public BaseSTCollisionRebound(FastTesterMain main) {
        super(main, BodyType.Dynamic, BodyType.Static);
        infoMsg("Press Space for change shape moving");
        infoMsg("Right Click for change pos position");
        infoMsg("Left click for change target");
        body2.setPosition(0, 0);
        changePosition(new Vector2(300, 500));
        body2.getPosition(target);
    }

    private void changeType() {
        bodyMove.bodyType = BodyType.Static;
        bodyMove.setVelocity(0, 0);
        Body targetBody = bodyMove == body1 ? body2 : body1;
        targetBody.bodyType = BodyType.Dynamic;
        bodyMove = targetBody;
        changePosition(bodyMove.getPosition(new Vector2()));
    }

    private void changePosition(Vector2 pos) {
        Body targetBody = bodyMove == body1 ? body2 : body1;

        targetBody.setPosition(0, 0);
        bodyMove.setPosition(pos);
        changeTarget();
    }

    private void changeTarget() {
        Vector2 dir = V2.directionTo(bodyMove.getPosition(new Vector2()), target, new Vector2());
        bodyMove.setVelocity(dir.scl(100));
    }

    @Override
    public void doRender(float dt) {
        super.doRender(dt);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(target.x - 10, target.y, target.x + 10, target.y);
        shapeRenderer.line(target.x, target.y - 10, target.x, target.y + 10);
        shapeRenderer.end();
    }

    @Override
    public String getTestExplication() {
        if (body1 == null || body2 == null)
            return null;
        return "Test Shape collision bounce between "
                + body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName()
                + " and " +
                body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
    }

    public InputProcessor addInputListener() {
        return new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                    changePosition(V2.tmp(unproject));
                }
                if (button == Input.Buttons.RIGHT) {
                    Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                    V2.set(target, unproject);
                    changeTarget();
                }


                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    changeType();
                }
                return false;
            }
        };
    }

}
