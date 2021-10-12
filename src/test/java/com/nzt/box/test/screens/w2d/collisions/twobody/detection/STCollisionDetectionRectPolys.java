package com.nzt.box.test.screens.w2d.collisions.twobody.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.math.shapes.builders.PolygonBuilder;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionDetectionRectPolys extends BaseSTCollisionDetection<PolygonShape, PolygonShape> {
    public STCollisionDetectionRectPolys(FastTesterMain main) {
        super(main);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return new PolygonShape(PolygonBuilder.rectangle(10, 300, true));
    }

    @Override
    protected PolygonShape createBodyShape2() {
        return new PolygonShape(PolygonBuilder.rectangle(10, 300, true));
    }
}
