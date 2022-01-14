package com.nzt.box.test.s_try.w2d.collisions.twobody.rebond;

import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryCollisionReboundPolygons extends BaseSTryCollisionRebound<PolygonShape, PolygonShape> {
    public STryCollisionReboundPolygons(FastTesterMain main) {
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
