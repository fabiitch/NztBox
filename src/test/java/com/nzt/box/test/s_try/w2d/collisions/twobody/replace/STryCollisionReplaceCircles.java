package com.nzt.box.test.s_try.w2d.collisions.twobody.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryCollisionReplaceCircles extends BaseSTryCollisionReplace<CircleShape,CircleShape> {
    public STryCollisionReplaceCircles(FastTesterMain main) {
        super(main);
    }


    @Override
    protected CircleShape createBodyShape1() {
        return createCircle(50);
    }

    @Override
    protected CircleShape createBodyShape2() {
        return createCircle(100);
    }
}
