package com.nzt.box.test.s_try.w2d.collisions.twobody.rebond;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionReboundRectangles extends BaseSTryCollisionRebound<RectangleShape, RectangleShape> {
    public STryCollisionReboundRectangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected void doBeginContact() {

    }

    @Override
    public void renderContactInfo(float dt) {

    }

    @Override
    protected RectangleShape createBodyShape1() {
        return createRectangle(100, 50);
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(80, 80);
    }
}
