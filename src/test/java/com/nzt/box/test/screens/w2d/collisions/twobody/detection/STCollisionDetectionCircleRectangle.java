package com.nzt.box.test.screens.w2d.collisions.twobody.detection;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionDetectionCircleRectangle extends BaseSTCollisionDetection<CircleShape, RectangleShape> {
    public STCollisionDetectionCircleRectangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected CircleShape createBodyShape1() {
        return createCircle(50);
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(100, 50);
    }


}
