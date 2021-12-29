package com.nzt.box.test.s_try.w2d.collisions.twobody.rebond;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionReboundCirclePolygon extends BaseSTryCollisionRebound<PolygonShape, CircleShape> {
    public STryCollisionReboundCirclePolygon(FastTesterMain main) {
        super(main);
    }

    @Override
    protected void doBeginContact() {

    }

    @Override
    public void renderContactInfo(float dt) {

    }

    @Override
    protected CircleShape createBodyShape2() {
        return createCircle(50);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return createPolygon1();
    }

    @Override
    public String getTestExplication() {
        if (body1 == null || body2 == null)
            return null;
        return "Test Rebound between "
                + body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName()
                + " and " +
                body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
    }
}
