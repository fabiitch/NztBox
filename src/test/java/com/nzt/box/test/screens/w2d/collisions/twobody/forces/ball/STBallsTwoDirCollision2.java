package com.nzt.box.test.screens.w2d.collisions.twobody.forces.ball;

import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STBallsTwoDirCollision2 extends Base2BallCollision {
    public STBallsTwoDirCollision2(FastTesterMain main) {
        super(main);
        afterClick(null);
    }

    @Override
    protected float getMass1() {
        return 1;
    }

    @Override
    protected float getMass2() {
        return 1;
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



    @Override
    public void afterClick(Vector2 clickPos) {
        ball1.setPosition(-200, 0);
        ball2.setPosition(0, -200);

        ball1.setVelocity(200, 0);
        ball2.setVelocity(0, 200);
    }

    @Override
    public String getTestExplication() {
        return "One ball should give force, second body start move at collision";
    }
}
