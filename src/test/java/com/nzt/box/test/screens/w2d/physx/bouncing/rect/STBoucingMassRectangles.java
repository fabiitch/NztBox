package com.nzt.box.test.screens.w2d.physx.bouncing.rect;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTBouncingMassBodies;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.bouncing.rectangle")
public class STBoucingMassRectangles extends BaseSTBouncingMassBodies {
    public STBoucingMassRectangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyDef bodyDef() {
        return new BodyDef(BodyType.Dynamic).mass(1).restitution(1).receive(1).transfert(1);
    }

    @Override
    protected BodyShape bodyShape() {
        return new RectangleShape(10, 5);
    }
}
