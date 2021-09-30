package com.nzt.box.test.screens.w2d.collisions.replace;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class ST2BodyCollisionReplaceCircleRectangle extends BaseST2BodyCollisionReplace<CircleShape, RectangleShape> {
    public ST2BodyCollisionReplaceCircleRectangle(FastTesterMain main) {
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
