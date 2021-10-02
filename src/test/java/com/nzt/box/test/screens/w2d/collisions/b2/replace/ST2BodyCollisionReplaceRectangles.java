package com.nzt.box.test.screens.w2d.collisions.b2.replace;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class ST2BodyCollisionReplaceRectangles extends BaseST2BodyCollisionReplace<RectangleShape, RectangleShape> {
    public ST2BodyCollisionReplaceRectangles(FastTesterMain main) {
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
