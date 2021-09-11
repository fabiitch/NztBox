package com.nzt.box.test.screens.w2d.physx.balls.collisions;

import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBallsTwoDirCollision extends BaseBallCollision {
    public STBallsTwoDirCollision(FastTesterMain main) {
        super(main);
        resetPos();
    }

    public void resetPos() {
        ball1.setPosition(-200, 0);
        ball2.setPosition(0, -200);

        ball1.setVelocity(200, 0);
        ball2.setVelocity(0, 200);
    }


    @Override
    public void afterClick(Vector2 clickPos) {
        resetPos();
    }

    @Override
    public String getTestExplication() {
        return "One ball should give force, second body start move at collision";
    }
}
