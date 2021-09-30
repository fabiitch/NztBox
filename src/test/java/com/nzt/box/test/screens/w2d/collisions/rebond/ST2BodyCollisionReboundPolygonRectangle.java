package com.nzt.box.test.screens.w2d.collisions.rebond;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class ST2BodyCollisionReboundPolygonRectangle extends BaseST2BodyCollisionRebound<PolygonShape, RectangleShape> {
    public ST2BodyCollisionReboundPolygonRectangle(FastTesterMain main) {
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
