package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;

public class World {

    int iterations = 10;
    int maxStepTime = 1 / 80;

    public ContactListener contactListener;

    public WorldHelper helper;
    public WorldData data;

    public World() {
        this.helper = new WorldHelper(this);
        this.data = new WorldData(this);
    }

    public void step(float dt) {
        float stepTime = dt / iterations;
        for (int nbIteraton = 0; nbIteraton < iterations; nbIteraton++) {
            Array<Body> bodies = data.bodies;
            for (int i = 0, n = bodies.size; i < n; i++) {
                Body body = bodies.get(i);
                if (!body.active)
                    continue;
                boolean move = body.move(stepTime);
                if (move || body.dirty) {
                    checkCollision(body);
                }
                body.dirty = false;
            }
        }
    }

    public void addBody(Body body) {
        data.addBody(body);
    }

    public void removeBody(Body body) {
        data.removeBody(body);
    }

    public void checkCollision(Body body) {
        Array<Body> bodies = data.bodies;
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body bodyTest = bodies.get(i);
            if (body != bodyTest && body.active) {
                testContact(body, bodyTest);
            }
        }
    }

    public void testContact(Body bodyA, Body bodyB) {
        //TODO find if alreadyContact
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            for (int j = 0, m = bodyB.fixtures.size; j < m; j++) {
                Fixture<?> fixtureA = bodyA.fixtures.get(i);
                Fixture<?> fixtureB = bodyB.fixtures.get(j);
                ContactFixture hasContact = data.getContact(fixtureA, fixtureB); //pas bon doit renvoyé une liste
                if (hasContact != null) {
                    boolean retry = hasContact.retry();
                    if (retry) {
                        if (hasContact.tickEveryStep && contactListener != null)
                            contactListener.continueContact(hasContact);
                        if (hasContact.isBlockingContact())
                            hasContact.fixtureA.replace(hasContact.fixtureB, hasContact);

                    } else {
                        if (contactListener != null)
                            contactListener.endContact(hasContact);
                        data.endContact(hasContact);
                    }
                } else {
                    boolean newC = fixtureA.testContact(fixtureB);
                    if (newC) {
                        ContactFixture newContact = ContactFixture.get(fixtureA, fixtureB);
                        if (contactListener != null)
                            contactListener.beginContact(newContact);
                        if (!newContact.ignoreContact) {
                            data.addContact(newContact);
                            if (newContact.enableContact && BoxUtils.isContactBlock(bodyA, bodyB)) {
                                fixtureA.replace(fixtureB, newContact);
                                fixtureA.rebound(fixtureB, newContact);
                            }
                        }
                    }
                }
            }
        }
    }
}
