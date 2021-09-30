package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.world.World;

public class BoxSTHelp {

    private World world;

    public BoxSTHelp(World world) {
        this.world = world;
    }

    protected Body createBall(int userData, BodyDef bodyDef) {
        Body body = new Body(bodyDef);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        fixture.userData = "" + userData;
        world.addBody(body);
        return body;
    }

}
