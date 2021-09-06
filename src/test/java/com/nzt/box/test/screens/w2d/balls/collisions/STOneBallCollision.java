package com.nzt.box.test.screens.w2d.balls.collisions;

import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STOneBallCollision extends BaseBallCollision {
    public STOneBallCollision(FastTesterMain main) {
        super(main);
        resetPos();
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
