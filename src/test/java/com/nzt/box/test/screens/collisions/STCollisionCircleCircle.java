package com.nzt.box.test.screens.collisions;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionCircleCircle extends BaseSTCollision {


    public STCollisionCircleCircle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape createBodyShape1() {
        return new CircleShape(new Circle(0, 0, 50));
    }

    @Override
    protected BodyShape createBodyShape2() {
        return new CircleShape(new Circle(0, 0, 50));
    }
}
