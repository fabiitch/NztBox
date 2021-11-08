package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.world.World;
import com.nzt.gdx.math.shapes.builders.PolygonBuilder;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;

public class BoxSTHelp {
    private World world;

    private int userDataBodyCount = 1;
    private int userDataFixtureCount = 1;

    public final BodyDef basicDynamicBodyDef = new BodyDef(BodyType.Dynamic).mass(1).transfert(1).receive(1);
    public final BodyDef basicStaticBodyDef = new BodyDef(BodyType.Static).mass(1).transfert(1).receive(1).restitution(1);

    public BoxSTHelp(World world) {
        this.world = world;
    }

    private Body createBody(BodyDef bodyDef, BodyShape bodyShape, Vector2 position, Vector2 velocity, String userData) {
        Body body = new Body(bodyDef);
        Fixture fixture = new Fixture(bodyShape);
        body.addFixture(fixture);
        if (userData == null) {
            body.userData = "B-" + userDataBodyCount++;
            fixture.userData = "F-" + userDataFixtureCount++;
        } else {
            body.userData = "B-" + userData;
            fixture.userData = "F-" + userData;
        }
        body.setPosition(position);
        body.setVelocity(velocity);
        world.addBody(body);
        return body;
    }

    public Body createCircle(float radius, BodyDef bodyDef, Vector2 pos, Vector2 velocity, String userData) {
        return createBody(bodyDef, new CircleShape(radius), pos, velocity, userData);
    }

    public Body createDynamicCircle(float radius, Vector2 pos, Vector2 velocity) {
        return createCircle(radius, basicDynamicBodyDef, pos, velocity, null);
    }

    public Body createRect(Rectangle rect, BodyDef bodyDef, Vector2 velocity, String userData) {
        return createRect(rect.width, rect.height, bodyDef, RectangleUtils.getCenter(rect, new Vector2()), velocity, userData);
    }


    public Body createRect(float witdh, float height, BodyDef bodyDef, Vector2 pos, Vector2 velocity, String userData) {
        return createBody(bodyDef, new RectangleShape(witdh, height), pos, velocity, userData);
    }

    public Body createPoly(Vector2[] vertices, BodyDef bodyDef, Vector2 pos, Vector2 velocity, String userData) {
        Polygon polygon = PolygonBuilder.polygon(vertices);
        return createBody(bodyDef, new PolygonShape(polygon), pos, velocity, userData);
    }
}
