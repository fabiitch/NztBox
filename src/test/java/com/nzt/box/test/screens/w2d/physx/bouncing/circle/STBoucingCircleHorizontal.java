package com.nzt.box.test.screens.w2d.physx.bouncing.circle;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.bouncing.circle")
public class STBoucingCircleHorizontal extends STBoucingCircle {
    public STBoucingCircleHorizontal(FastTesterMain main) {
        super(main);
        body.setVelocity(500, 0);
    }

    @Override
    protected BodyDef bodyDef() {
        return new BodyDef(BodyType.Dynamic).mass(1).restitution(1).receive(1).transfert(1);
    }

}
