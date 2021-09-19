package com.nzt.box.test.screens.w2d.physx.bouncing.circle;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTBouncingMassBodies;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBouncingMassCircles extends BaseSTBouncingMassBodies {
    public STBouncingMassCircles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyDef bodyDef() {
        BodyDef bodyDef = new BodyDef(BodyType.Dynamic);
        bodyDef.mass = 1;
        bodyDef.restitution = 1;
        bodyDef.transfert = 1;
        return bodyDef;
    }

    @Override
    protected BodyShape bodyShape() {
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        return shape;
    }

}
