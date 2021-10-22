package com.nzt.box.contact.data;

import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;

public class ContactFixture implements Pool.Poolable {
    public Fixture<?> fixtureA;
    public Fixture<?> fixtureB;

    public boolean doCollision = true; //no rebound/forces applied but call method
    public boolean doRebound = false;
    public boolean calculCollisionData = false;

    public boolean continueContact = false; //call contactListener.continueContact every step
    public boolean callNextMethods = true; //contact dont call next methods but apply forces/rebound

    public final CollisionData collisionData;

    public ContactFixture() {
        collisionData = new CollisionData();
    }

    public boolean retry() {
        int fastCheck = ContactUtils.fastCheck(fixtureA, fixtureB);
        if (fastCheck == 1)
            return true;
        if (fastCheck == -1)
            return false;
        return fixtureA.testContact(fixtureB);
    }

    public boolean imFixtureA(Fixture<?> fixture) {
        return fixture == fixtureA;
    }

    public boolean imBodyA(Body body) {
        return body == fixtureA.body;
    }

    public boolean hasBody(Body body) {
        return this.fixtureA.body == body || this.fixtureB.body == body;
    }

    public boolean hasBodies(Body bodyA, Body bodyB) {
        return hasBody(bodyA) && hasBody(bodyB);
    }

    public boolean hasFixture(Fixture fixture) {
        return this.fixtureA == fixture || this.fixtureB == fixture;
    }

    public boolean hasFixtures(Fixture fixtureA, Fixture fixtureB) {
        return hasFixture(fixtureA) && hasFixture(fixtureB);
    }

    public String debug() {
        return "ContactFixture = [BodyA=" + fixtureA.body.userData + ", FixtureA=" + fixtureA.userData + "\n" +
                "&& BodyB=" + fixtureB.body.userData + " FixtureB=" + fixtureB.userData + "]";
    }

    @Override
    public void reset() {
        fixtureA = null;
        fixtureB = null;

        callNextMethods = true;
        continueContact = false;

        doCollision = true;

        collisionData.reset();
    }

}
