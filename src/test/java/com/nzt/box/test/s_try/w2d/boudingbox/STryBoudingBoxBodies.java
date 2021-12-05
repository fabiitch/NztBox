package com.nzt.box.test.s_try.w2d.boudingbox;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;

public class STryBoudingBoxBodies extends BaseSTryBoudingBox {
    public STryBoudingBoxBodies(FastTesterMain main) {
        super(main);

        Body body = new Body(BodyType.Dynamic);
        Fixture fixtureA = new Fixture(new CircleShape(10));
        body.addFixture(fixtureA);
        Fixture fixtureB = new Fixture(new CircleShape(20));
        body.addFixture(fixtureB);
        Fixture fixtureC = new Fixture(new RectangleShape(100, 10));
        body.addFixture(fixtureC);

        body.setVelocity(new Vector2(1, 0).setToRandomDirection().setLength(150));

        world.addBody(body);
    }

    @Override
    public String getTestExplication() {
        return "Test draw boudingbox body";
    }
}
