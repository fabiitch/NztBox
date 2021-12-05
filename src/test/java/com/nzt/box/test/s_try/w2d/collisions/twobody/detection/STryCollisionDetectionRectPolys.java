package com.nzt.box.test.s_try.w2d.collisions.twobody.detection;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.math.shapes.builders.PolygonBuilder;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STryCollisionDetectionRectPolys extends BaseSTryCollisionDetection<PolygonShape, PolygonShape> {
    public STryCollisionDetectionRectPolys(FastTesterMain main) {
        super(main);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return new PolygonShape(PolygonBuilder.rectangle(50, 300, true));
    }

    @Override
    protected PolygonShape createBodyShape2() {
        return new PolygonShape(PolygonBuilder.rectangle(100, 300, true));
    }
}
