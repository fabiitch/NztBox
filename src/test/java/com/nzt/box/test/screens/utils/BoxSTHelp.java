package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.world.World;

public class BoxSTHelp {
    private World world;

    private int userDataCount = 1;
    public BodyDef basicDynamicBd = new BodyDef(BodyType.Dynamic).mass(1).transfert(1).receive(1);

    public BoxSTHelp(World world) {
        this.world = world;
    }

    public Body createBall(BodyDef bodyDef, Vector2 pos, Vector2 velocity, String userData) {
        Body body = new Body(bodyDef);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        fixture.userData = userData;

        body.setPosition(pos);
        body.setVelocity(velocity);
        world.addBody(body);
        return body;
    }

    public Body createBall(Vector2 pos, Vector2 velocity) {
        return createBall(basicDynamicBd, pos, velocity, "" + userDataCount++);
    }

}
