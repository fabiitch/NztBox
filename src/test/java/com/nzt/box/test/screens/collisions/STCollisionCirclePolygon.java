package com.nzt.box.test.screens.collisions;

import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionCirclePolygon extends BaseSTCollision<PolygonShape, CircleShape> {
    public STCollisionCirclePolygon(FastTesterMain main) {
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
