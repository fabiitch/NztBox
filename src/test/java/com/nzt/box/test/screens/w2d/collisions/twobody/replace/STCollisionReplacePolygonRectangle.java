package com.nzt.box.test.screens.w2d.collisions.twobody.replace;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionReplacePolygonRectangle extends BaseSTCollisionReplace<PolygonShape, RectangleShape> {
    public STCollisionReplacePolygonRectangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected PolygonShape createBodyShape1() {
        return createPolygon1();
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(100,50);
    }
}