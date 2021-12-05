package com.nzt.box.test.s_try.w2d.boudingbox;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STryBoudingBoxRotation extends BaseSTryBoudingBox {
    public STryBoudingBoxRotation(FastTesterMain main) {
        super(main);
        Body body = new Body(BodyType.Dynamic);
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        Fixture fixtureA = new Fixture(new PolygonShape(new Polygon(vertices)));
        body.addFixture(fixtureA);
        world.addBody(body);
        body.angularVelocity = 10;
    }

    @Override
    public String getTestExplication() {
        return "Rotation bouding box";
    }
}
