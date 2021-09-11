package com.nzt.box.contact.data;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;

public class ContactFixture implements Pool.Poolable {
    public Fixture<?> fixtureA;
    public Fixture<?> fixtureB;

    public boolean continueContact = false; //call contactListener.continueContact every step
    public boolean callNextMethods = true; //contact dont call next methods but apply forces/rebound

    public boolean doCollision = true; //no rebound and forces
    public boolean doForces = true; //no forces are computed only rebound


    public CollisionData collisionData;

    public ContactFixture() {
        collisionData = new CollisionData();
    }

    public boolean retry() {
        boolean fastCheck = ContactUtils.canContact(fixtureA, fixtureB);
        if (!fastCheck)
            return false;

        return fixtureA.testContact(fixtureB);
    }

    public boolean imFixtureA(Fixture<?> fixture) {
        return fixture == fixtureA;
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

    public boolean isBlockingContact() {
        return doCollision && BoxUtils.isContactBlock(fixtureA.body, fixtureB.body);
    }

    public String debug() {
        return "ContactFixture = [BodyA=" + fixtureA.body.userData + ", FixtureA=" + fixtureA.userData + "\n" +
                "&& BodyB=" + fixtureB.body.userData + " FixtureB=" + fixtureB.userData + "]";
    }

    public static ContactFixture get(Fixture fixtureA, Fixture fixtureB) {
        ContactFixture contactFixture = Pools.obtain(ContactFixture.class);
        contactFixture.fixtureA = fixtureA;
        contactFixture.fixtureB = fixtureB;
        return contactFixture;
    }

    @Override
    public void reset() {
        fixtureA = null;
        fixtureB = null;

        callNextMethods = true;
        continueContact = false;

        doCollision = true;
        doForces = true;

        collisionData.reset();
    }

}
