package com.nzt.box.test.s_try.w2d.collisions.twobody.replace;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryCollisionReplacePolygons extends BaseSTryCollisionReplace<PolygonShape, PolygonShape> {
    public STryCollisionReplacePolygons(FastTesterMain main) {
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
