package com.nzt.box.test.s_try.w2d.collisions.twobody.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STryCollisionReplaceCircleRectangle extends BaseSTryCollisionReplace<CircleShape, RectangleShape> {
    public STryCollisionReplaceCircleRectangle(FastTesterMain main) {
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
