package com.nzt.box.test.screens.w2d.collisions.b2.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class ST2BodyCollisionDetectionPolygons extends BaseST2BodyCollisionDetection<PolygonShape, PolygonShape> {
    public ST2BodyCollisionDetectionPolygons(FastTesterMain main) {
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
