package com.nzt.box.test.screens.w2d.collisions.b2.replace;

import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class ST2BodyCollisionReplacePolygonRectangle extends BaseST2BodyCollisionReplace<PolygonShape, RectangleShape> {
    public ST2BodyCollisionReplacePolygonRectangle(FastTesterMain main) {
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
