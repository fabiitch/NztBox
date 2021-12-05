package com.nzt.box.test.s_try.w2d.collisions.twobody.detection;

import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STryCollisionDetectionCircles extends BaseSTryCollisionDetection<CircleShape, CircleShape> {


    public STryCollisionDetectionCircles(FastTesterMain main) {
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
