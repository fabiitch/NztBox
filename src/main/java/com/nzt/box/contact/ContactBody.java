package com.nzt.box.contact;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;

public class ContactBody implements Pool.Poolable {

    public Fixture fixtureA;
    public Fixture fixtureB;
    public boolean tickEveryStep = false;

    public ContactBody() {
    }

    public boolean retry() {
        return fixtureA.testContact(fixtureB);
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

    public static ContactBody get(Fixture fixtureA, Fixture fixtureB) {
        ContactBody contactBody = Pools.obtain(ContactBody.class);
        contactBody.fixtureA = fixtureA;
        contactBody.fixtureB = fixtureB;
        return contactBody;
    }

    @Override
    public void reset() {
        System.out.println("Called");
        fixtureA = null;
        fixtureB = null;
        tickEveryStep = false;
    }
}
