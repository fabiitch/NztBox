package com.nzt.box.test.screens.w2d.collisions.b2.forces.ball;

import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STOne2BallCollision extends Base2BallCollision {
    public STOne2BallCollision(FastTesterMain main) {
        super(main);
        resetPos();
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
        return 1;
    }

    @Override
    protected float getTransfert1() {
        return 0.5f;
    }

    @Override
    protected float getTransfert2() {
        return 1;
    }

    @Override
    public String getTestExplication() {
        return "One ball move";
    }

    public void resetPos() {
        ball1.setPosition(-200, 0);
        ball2.setPosition(0, 0);

        ball1.setVelocity(200, 0);
        ball2.setVelocity(0, 0);
    }

    @Override
    public void afterClick(Vector2 clickPos) {
        resetPos();
    }


}
