package com.nzt.box.test.s_try.w2d.collisions.twobody.rebond;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionReboundCircleRectangle extends BaseSTryCollisionRebound<CircleShape, RectangleShape> {
    public STryCollisionReboundCircleRectangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected void doBeginContact() {

    }

    @Override
    public void renderContactInfo(float dt) {

    }

    @Override
    protected CircleShape createBodyShape1() {
        return createCircle(50);
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(100, 50);
    }


}
