package com.nzt.box.test.screens.collisions.rebond;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionReboundPolygons extends BaseSTCollisionRebound<PolygonShape, PolygonShape> {
    public STCollisionReboundPolygons(FastTesterMain main) {
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
