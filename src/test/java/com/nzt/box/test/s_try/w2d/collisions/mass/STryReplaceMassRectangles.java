package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryReplaceMassRectangles extends BaseSTryReplaceMassBodies {
    public STryReplaceMassRectangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        return new RectangleShape(50, 50);
    }
}
