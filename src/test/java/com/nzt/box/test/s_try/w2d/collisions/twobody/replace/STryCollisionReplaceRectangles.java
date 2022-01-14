package com.nzt.box.test.s_try.w2d.collisions.twobody.replace;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryCollisionReplaceRectangles extends BaseSTryCollisionReplace<RectangleShape, RectangleShape> {
    public STryCollisionReplaceRectangles(FastTesterMain main) {
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
