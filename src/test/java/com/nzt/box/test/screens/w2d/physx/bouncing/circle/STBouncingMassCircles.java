package com.nzt.box.test.screens.w2d.physx.bouncing.circle;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTBouncingMassBodies;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.bouncing.circle")
public class STBouncingMassCircles extends BaseSTBouncingMassBodies {
    public STBouncingMassCircles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyDef bodyDef() {
        return new BodyDef(BodyType.Dynamic).mass(1).restitution(1).receive(1).transfert(1);
    }

    @Override
    protected BodyShape bodyShape() {
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        return shape;
    }

}
