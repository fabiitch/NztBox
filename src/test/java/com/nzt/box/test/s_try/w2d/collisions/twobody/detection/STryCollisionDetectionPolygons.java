package com.nzt.box.test.s_try.w2d.collisions.twobody.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionDetectionPolygons extends BaseSTryCollisionDetection<PolygonShape, PolygonShape> {
    public STryCollisionDetectionPolygons(FastTesterMain main) {
        super(main);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return createPolygon1();
    }

    @Override
    protected PolygonShape createBodyShape2() {
        return createPolygon1();
    }
}
