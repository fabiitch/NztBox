package com.nzt.box.test.screens.w2d.collisions.detection;

import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class ST2BodyCollisionDetectionRectangles extends BaseST2BodyCollisionDetection<RectangleShape, RectangleShape> {
    public ST2BodyCollisionDetectionRectangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected RectangleShape createBodyShape1() {
        return createRectangle(100, 50);
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(80, 80);
    }
}
