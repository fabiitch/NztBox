package com.nzt.box.test.screens.collisions.rebond;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionReboundRectangles extends BaseSTCollisionRebound<RectangleShape, RectangleShape> {
    public STCollisionReboundRectangles(FastTesterMain main) {
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
