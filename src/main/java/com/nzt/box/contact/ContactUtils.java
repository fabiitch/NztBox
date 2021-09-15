package com.nzt.box.contact;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;

public class ContactUtils {

    private static final Vector2 tmp1 = new Vector2(); //TODO
    private static final Vector2 tmp2 = new Vector2(); //TODO

    public static ContactFixture getNewContact(Fixture fixtureA, Fixture fixtureB) {
        ContactFixture contactFixture = Pools.obtain(ContactFixture.class);
        contactFixture.fixtureA = fixtureA;
        contactFixture.fixtureB = fixtureB;
        return contactFixture;
    }

    public static boolean canContact(Fixture fixtureA, Fixture fixtureB) {
        Body bodyA = fixtureA.body;
        Body bodyB = fixtureB.body;
        bodyA.getPosition(tmp1);
        bodyB.getPosition(tmp2);
        float dstBodies = tmp1.dst(tmp2);
        return dstBodies < fixtureA.bodyShape.maxDst + fixtureB.bodyShape.maxDst;
    }

    public static boolean canContact(Body bodyA, Fixture fixtureB) {
        bodyA.getPosition(tmp1);
        Body bodyB = fixtureB.body;
        bodyB.getPosition(tmp2);
        float dstBodies = tmp1.dst(tmp2);
        return dstBodies < bodyA.maxDstFixture + fixtureB.bodyShape.maxDst;
    }

    public static boolean canContact(Body bodyA, Body bodyB) {
        bodyA.getPosition(tmp1);
        bodyB.getPosition(tmp2);
        float dstBodies = tmp1.dst(tmp2);
        return dstBodies < bodyA.maxDstFixture + bodyB.maxDstFixture;
    }
}
