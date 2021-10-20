package com.nzt.box.contact;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;

public class ContactUtils {

    private static final Vector2 tmp1 = new Vector2(); //TODO
    private static final Vector2 tmp2 = new Vector2(); //TODO

    public static boolean shouldTestContact(Body bodyA, Body bodyB) {
        BodyType typeA = bodyA.bodyType;
        BodyType typeB = bodyB.bodyType;

        if (typeA == BodyType.Dynamic || typeB == BodyType.Dynamic)
            return true;

        if (typeA == BodyType.Kinematic || typeB == BodyType.Kinematic)
            return true;
        return false;
    }

    public static ContactFixture getNewContact(Fixture fixtureA, Fixture fixtureB) {
        ContactFixture contactFixture = Pools.obtain(ContactFixture.class);
        contactFixture.fixtureA = fixtureA;
        contactFixture.fixtureB = fixtureB;

        contactFixture.doCollision = BoxUtils.isContactBlock(fixtureA.body, fixtureB.body);
        contactFixture.calculCollisionData = contactFixture.doCollision;
        contactFixture.doRebound = BoxUtils.isTwoType(fixtureA.body, fixtureB.body, BodyType.Static, BodyType.Dynamic);

        return contactFixture;
    }

    /**
     * -1 cant, 0 can, 1 sure
     *
     * @return
     */
    public static int fastCheck(Fixture fixtureA, Fixture fixtureB) {
        Body bodyA = fixtureA.body;
        Body bodyB = fixtureB.body;
        bodyA.getPosition(tmp1);
        bodyB.getPosition(tmp2);
        float dstBodies = tmp1.dst(tmp2);
        if (dstBodies <= fixtureA.bodyShape.minDst + fixtureB.bodyShape.minDst)
            return 1;
        if (dstBodies <= fixtureA.bodyShape.maxDst + fixtureB.bodyShape.maxDst)
            return 0;
        return -1;
    }

    public static int fastCheck(Body bodyA, Body bodyB) {
        bodyA.getPosition(tmp1);
        bodyB.getPosition(tmp2);
        float dstBodies = tmp1.dst(tmp2);
        if (dstBodies <= bodyA.minDstFixture + bodyB.minDstFixture)
            return 1;
        if (dstBodies <= bodyA.maxDstFixture + bodyB.maxDstFixture)
            return 0;
        return -1;
    }

    public static boolean canContact(Fixture fixtureA, Fixture fixtureB) {
        return fastCheck(fixtureA, fixtureB) >= 0;
    }

    public static int fastCheck(Body bodyA, Fixture fixtureB) {
        bodyA.getPosition(tmp1);
        Body bodyB = fixtureB.body;
        bodyB.getPosition(tmp2);
        float dstBodies = tmp1.dst(tmp2);
        if (dstBodies <= bodyA.minDstFixture + fixtureB.bodyShape.minDst)
            return 1;
        if (dstBodies <= bodyA.maxDstFixture + fixtureB.bodyShape.maxDst)
            return 0;
        return -1;
    }

    public static boolean canContact(Body bodyA, Fixture fixtureB) {
        return fastCheck(bodyA, fixtureB) >= 0;
    }

    public static boolean canContact(Body bodyA, Body bodyB) {
        return fastCheck(bodyA, bodyB) >= 0;
    }
}
