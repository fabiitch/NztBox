package com.nzt.box.test.s_try.w2d.collisions.twobody.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionReplaceCirclePolygon extends BaseSTryCollisionReplace<PolygonShape, CircleShape> {
    public STryCollisionReplaceCirclePolygon(FastTesterMain main) {
        super(main);
    }

    @Override
    protected CircleShape createBodyShape2() {
        return createCircle(50);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return createPolygon1();
    }
}
