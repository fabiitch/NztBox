package com.nzt.box.test.screens.collisions;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionCircles extends BaseSTCollision<CircleShape, CircleShape> {


    public STCollisionCircles(FastTesterMain main) {
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
