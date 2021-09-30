package com.nzt.box.test.screens.w2d.collisions.replace;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class ST2BodyCollisionReplacePolygons extends BaseST2BodyCollisionReplace<PolygonShape, PolygonShape> {
    public ST2BodyCollisionReplacePolygons(FastTesterMain main) {
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
