package com.nzt.box.test.screens.w2d.physx.bouncing.rect;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTOneBodyBouncing;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBoucingRectangle extends BaseSTOneBodyBouncing {
    public STBoucingRectangle(FastTesterMain main) {
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
        return new RectangleShape(50, 100);
    }
}
