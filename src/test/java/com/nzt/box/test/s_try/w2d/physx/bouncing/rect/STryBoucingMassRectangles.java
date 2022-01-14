package com.nzt.box.test.s_try.w2d.physx.bouncing.rect;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.s_try.w2d.physx.bouncing.BaseSTryBouncingMassBodies;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.physx.bouncing.rectangle")
public class STryBoucingMassRectangles extends BaseSTryBouncingMassBodies {
    public STryBoucingMassRectangles(FastTesterMain main) {
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
