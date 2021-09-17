package com.nzt.box.test.screens.w2d.collisions.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBallAsyncCollision extends BaseBallCollision {

    private Body bodyRect;

    public STBallAsyncCollision(FastTesterMain main) {
        super(main);
        world.destroyBody(ball2);
        bodyRect = new Body(BodyType.Dynamic);
        bodyRect.mass = getMass2();
        bodyRect.userData = "2";
        bodyRect.transfert = getTransfert2();
        bodyRect.restitution = getRestitution2();
        RectangleShape rectangleShape = new RectangleShape(50, 600);
        Fixture fixtureRect = new Fixture(rectangleShape);
        bodyRect.addFixture(fixtureRect);
        world.addBody(bodyRect);
        afterClick(null);
    }

    @Override
    protected float getMass1() {
        return 1;
    }

    @Override
    protected float getMass2() {
        return 10;
    }

    @Override
    protected float getRestitution1() {
        return 0;
    }

    @Override
    protected float getRestitution2() {
        return 0;
    }

    @Override
    protected float getTransfert1() {
        return 1;
    }

    @Override
    protected float getTransfert2() {
        return 1;
    }

    Vector2 velBall = new Vector2();
    Vector2 velRect = new Vector2();

    @Override
    public void doRender(float dt) {
        debugMsg("Ball velocity", ball1.getVelocity(velBall));
        debugMsg("Rect velocity", bodyRect.getVelocity(velRect));
    }

    @Override
    public void afterClick(Vector2 clickPos) {
        ball1.setPosition(-500, 0);
        ball1.setVelocity(200, 0);
        bodyRect.setPosition(0, 200);
        bodyRect.setVelocity(0, -50);

    }

    @Override
    public String getTestExplication() {
        return "only ball should give force";
    }
}
