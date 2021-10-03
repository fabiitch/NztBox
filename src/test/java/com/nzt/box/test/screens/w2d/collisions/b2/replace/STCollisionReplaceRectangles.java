package com.nzt.box.test.screens.w2d.collisions.b2.replace;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionReplaceRectangles extends BaseSTCollisionReplace<RectangleShape, RectangleShape> {
    public STCollisionReplaceRectangles(FastTesterMain main) {
        super(main);
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
