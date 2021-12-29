package com.nzt.box.test.s_try.w2d.collisions.twobody.forces.ball;

import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STry2BallTwoSpeedCollision extends BaseSTry2BallCollision {
    public STry2BallTwoSpeedCollision(FastTesterMain main) {
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
        ball1.setPosition(-300, 0);
        ball2.setPosition(-200, 0);

        ball1.setVelocity(200, 0);
        ball2.setVelocity(100, 0);
    }

    @Override
    public String getTestExplication() {
        return "Test two speed same Dir";
    }
}
