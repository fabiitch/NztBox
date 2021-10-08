package com.nzt.box.test.screens.w2d.collisions.twobody.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionDetectionPolygons extends BaseSTCollisionDetection<PolygonShape, PolygonShape> {
    public STCollisionDetectionPolygons(FastTesterMain main) {
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