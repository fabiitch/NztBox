package com.nzt.box.test.screens.w2d.collisions.replace.mass;

import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STReplaceMassRectangles extends BaseSTReplaceMassBodies {
    public STReplaceMassRectangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        return new RectangleShape(50, 50);
    }
}