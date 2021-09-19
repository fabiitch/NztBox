package com.nzt.box.test.screens.w2d.physx.bouncing.polygons;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTOneBodyBouncing;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBouncingPolygon extends BaseSTOneBodyBouncing {
    public STBouncingPolygon(FastTesterMain main) {
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
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        return new PolygonShape(new Polygon(vertices));
    }
}
