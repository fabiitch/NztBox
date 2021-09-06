package com.nzt.box.test.screens.w2d.collisions.rebond;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionReboundPolygons extends BaseSTCollisionRebound<PolygonShape, PolygonShape> {
    public STCollisionReboundPolygons(FastTesterMain main) {
        super(main);
    }

    @Override
    protected void doBeginContact() {

    }

    @Override
    public void renderContactInfo(float dt) {

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
