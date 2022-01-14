package com.nzt.box.test.s_try.w2d.collisions.twobody.rebond;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryCollisionReboundPolygonRectangle extends BaseSTryCollisionRebound<PolygonShape, RectangleShape> {
    public STryCollisionReboundPolygonRectangle(FastTesterMain main) {
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
    protected RectangleShape createBodyShape2() {
        return createRectangle(100,50);
    }
}
