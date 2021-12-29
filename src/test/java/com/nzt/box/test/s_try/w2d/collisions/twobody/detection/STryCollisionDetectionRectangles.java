package com.nzt.box.test.s_try.w2d.collisions.twobody.detection;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionDetectionRectangles extends BaseSTryCollisionDetection<RectangleShape, RectangleShape> {
    public STryCollisionDetectionRectangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected RectangleShape createBodyShape1() {
        return createRectangle(100, 50);
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(80, 80);
    }
}
