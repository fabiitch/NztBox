package com.nzt.box.test.screens.w2d.collisions.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class ST2BodyCollisionReplaceCirclePolygon extends BaseST2BodyCollisionReplace<PolygonShape, CircleShape> {
    public ST2BodyCollisionReplaceCirclePolygon(FastTesterMain main) {
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
