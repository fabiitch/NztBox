package com.nzt.box.test.s_try.w2d.collisions.twobody.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STryCollisionDetectionPolygonRectangle extends BaseSTryCollisionDetection<PolygonShape, RectangleShape> {
    public STryCollisionDetectionPolygonRectangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return createPolygon1();
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(100,50);
    }
}
