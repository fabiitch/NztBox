package com.nzt.box.test.screens.w2d.collisions.twobody.detection;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionDetectionCirclePolygon extends BaseSTCollisionDetection<PolygonShape, CircleShape> {
    public STCollisionDetectionCirclePolygon(FastTesterMain main) {
        super(main);
    }

    @Override
    protected CircleShape createBodyShape2() {
        return createCircle(50);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return createPolygon1();
    }
}
