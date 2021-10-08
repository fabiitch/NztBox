package com.nzt.box.test.screens.w2d.collisions.twobody.rebond;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionReboundPolygonRectangle extends BaseSTCollisionRebound<PolygonShape, RectangleShape> {
    public STCollisionReboundPolygonRectangle(FastTesterMain main) {
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
