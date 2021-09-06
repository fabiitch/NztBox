package com.nzt.box.test.screens.w2d.collisions.replace;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionReplacePolygons extends BaseSTCollisionReplace<PolygonShape, PolygonShape> {
    public STCollisionReplacePolygons(FastTesterMain main) {
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
