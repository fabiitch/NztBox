package com.nzt.box.test.s_try.w2d.physx.bouncing.circle;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.physx.bouncing.circle")
public class STryBoucingCircleHorizontal extends STryBoucingCircle {
    public STryBoucingCircleHorizontal(FastTesterMain main) {
        super(main);
        body.setVelocity(500, 0);
    }

    @Override
    protected BodyDef bodyDef() {
        return new BodyDef(BodyType.Dynamic).mass(1).restitution(1).receive(1).transfert(1);
    }

}
