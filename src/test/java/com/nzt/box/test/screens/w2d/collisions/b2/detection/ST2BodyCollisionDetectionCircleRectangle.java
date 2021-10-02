package com.nzt.box.test.screens.w2d.collisions.b2.detection;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class ST2BodyCollisionDetectionCircleRectangle extends BaseST2BodyCollisionDetection<CircleShape, RectangleShape> {
    public ST2BodyCollisionDetectionCircleRectangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected CircleShape createBodyShape1() {
        return createCircle(50);
    }

    @Override
    protected RectangleShape createBodyShape2() {
        return createRectangle(100, 50);
    }


}
