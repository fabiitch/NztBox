package com.nzt.box.test.s_try.w2d.physx.bouncing.polygons;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.test.s_try.w2d.physx.bouncing.BaseSTryBouncingMassBodies;
import com.nzt.gdx.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.physx.bouncing.polygon")
public class STryBoucingMassPolygons extends BaseSTryBouncingMassBodies {
    public STryBoucingMassPolygons(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyDef bodyDef() {
        return new BodyDef(BodyType.Dynamic).mass(1).restitution(1).receive(1).transfert(1);
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        return new PolygonShape(new Polygon(vertices));
    }
}
