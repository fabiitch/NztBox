package com.nzt.box.test.screens.collisions.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionDetectionPolygonRectangle extends BaseSTCollisionDetection<PolygonShape, RectangleShape> {
    public STCollisionDetectionPolygonRectangle(FastTesterMain main) {
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