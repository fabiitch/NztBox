package com.nzt.box.test.screens.collisions.rebond;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionReboundCircleRectangle extends BaseSTCollisionRebound<CircleShape, RectangleShape> {
    public STCollisionReboundCircleRectangle(FastTesterMain main) {
        super(main);
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
