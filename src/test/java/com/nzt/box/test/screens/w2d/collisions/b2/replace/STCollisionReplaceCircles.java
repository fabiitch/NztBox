package com.nzt.box.test.screens.w2d.collisions.b2.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionReplaceCircles extends BaseSTCollisionReplace<CircleShape,CircleShape> {
    public STCollisionReplaceCircles(FastTesterMain main) {
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
