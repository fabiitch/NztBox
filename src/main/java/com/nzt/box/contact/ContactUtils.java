package com.nzt.box.contact;

import com.badlogic.gdx.utils.Pools;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;

public class ContactUtils {

    public static boolean shouldTestContact(Body bodyA, Body bodyB) {
        if (bodyA == bodyB)
            return false;
        if (!bodyA.active || !bodyB.active)
            return false;

        BodyType typeA = bodyA.bodyType;
        BodyType typeB = bodyB.bodyType;
        if (typeA == BodyType.Dynamic || typeB == BodyType.Dynamic)
            return true;

        if (typeA == BodyType.Kinematic && typeB == BodyType.Kinematic)
            return true;
        return false;
    }

    public static ContactFixture getNewContact(Fixture fixtureA, Fixture fixtureB) {
        ContactFixture contactFixture = Pools.obtain(ContactFixture.class);
        contactFixture.fixtureA = fixtureA;
        contactFixture.fixtureB = fixtureB;

        contactFixture.doCollision = BoxUtils.isContactBlock(fixtureA.body, fixtureB.body);
        contactFixture.doCalculData = contactFixture.doCollision;
        contactFixture.doRebound = BoxUtils.isTwoType(fixtureA.body, fixtureB.body, BodyType.Static, BodyType.Dynamic);

        return contactFixture;
    }

    public static boolean fastCheck(Fixture fixtureA, Fixture fixtureB) {
        return RectangleUtils.overlapsStick(fixtureA.getBoundingRectangle(), fixtureB.getBoundingRectangle());
    }

    public static boolean fastCheck(Body bodyA, Body bodyB) {
        return RectangleUtils.overlapsStick(bodyA.boundingBox, bodyB.boundingBox);
    }

    public static boolean fastCheck(Body bodyA, Fixture fixtureB) {
        return RectangleUtils.overlapsStick(bodyA.boundingBox, fixtureB.getBoundingRectangle());
    }
}
