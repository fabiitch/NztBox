package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryReplaceMassCircles extends BaseSTryReplaceMassBodies {
    public STryReplaceMassCircles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        return new CircleShape(25);
    }
}
