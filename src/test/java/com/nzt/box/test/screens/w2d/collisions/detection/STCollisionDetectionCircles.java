package com.nzt.box.test.screens.w2d.collisions.detection;

import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionDetectionCircles extends BaseSTCollisionDetection<CircleShape, CircleShape> {


    public STCollisionDetectionCircles(FastTesterMain main) {
        super(main);
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
