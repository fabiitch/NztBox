package com.nzt.box.test.screens.w2d.collisions.b2.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionReplaceCirclePolygon extends BaseSTCollisionReplace<PolygonShape, CircleShape> {
    public STCollisionReplaceCirclePolygon(FastTesterMain main) {
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
