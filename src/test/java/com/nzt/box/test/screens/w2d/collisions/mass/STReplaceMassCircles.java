package com.nzt.box.test.screens.w2d.collisions.mass;

import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STReplaceMassCircles extends BaseSTReplaceMassBodies {
    public STReplaceMassCircles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        return new CircleShape(25);
    }
}
