package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;
import com.nzt.box.contact.compute.ContactForces;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;

public class World {

    public ContactListener contactListener;
    public ContactForces contactForces;
    public WorldHelper helper;
    public WorldData data;

    public float stepTime;
    private float accumulator = 0f;

    public World(float stepTime) {
        this.stepTime = stepTime;
        this.helper = new WorldHelper(this);
        this.data = new WorldData(this);
        this.contactForces = new ContactForces();
    }

    public World() {
        this(1 / 300f);
    }

    public void step(float dt) {
        accumulator += dt;
        if (accumulator >= dt) {
            while (accumulator >= dt) {
                Array<Body> bodies = data.bodies;
                for (int i = 0, n = bodies.size; i < n; i++) {
                    Body body = bodies.get(i);
                    if (!body.active || body.bodyType == BodyType.Static)
                        continue;
                    boolean move = body.move(stepTime);
                    if (move || body.dirty) {
                        checkCollision(body, stepTime);
                    }
                    body.dirty = false;
                }
                accumulator -= stepTime;
            }
        }
    }

    public void addBody(Body body) {
        data.addBody(body);
    }

    public void destroyBody(Body body) {
        data.destroyBody(body);
    }

    public void checkCollision(Body body, float stepTime) {
        Array<Body> bodies = data.bodies;
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body bodyTest = bodies.get(i);
            if (body != bodyTest && body.active) {
                testContact(body, bodyTest, stepTime);
            }
        }
    }

    public void testContact(Body bodyA, Body bodyB, float stepTime) {
        ContactBody contactBody = data.getContact(bodyA, bodyB);
        if (contactBody == null) {
            if (!ContactUtils.canContact(bodyA, bodyB)) {
                return;
            }
        }
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            Fixture<?> fixtureA = bodyA.fixtures.get(i);
            if (contactBody == null) {
                boolean fixtureCanContact = ContactUtils.canContact(bodyB, fixtureA);
                if (!fixtureCanContact) {
                    continue;
                }
            }
            for (int j = 0, m = bodyB.fixtures.size; j < m; j++) {
                Fixture<?> fixtureB = bodyB.fixtures.get(j);
                ContactFixture hasContact = data.getContact(fixtureA, fixtureB);
                if (hasContact != null) {
                    boolean retry = hasContact.retry();
                    if (retry) {
                        if (hasContact.continueContact && contactListener != null)
                            contactListener.continueContact(hasContact);
                        if (hasContact.doCollision)
                            hasContact.fixtureA.replace(hasContact.fixtureB, hasContact);
                    } else {
                        if (contactListener != null && hasContact.callNextMethods)
                            contactListener.endContact(hasContact);
                        data.endContact(hasContact);
                    }
                } else {
                    boolean fastCheck = ContactUtils.canContact(fixtureA, fixtureB);
                    if (!fastCheck)
                        continue;
                    boolean newC = fixtureA.testContact(fixtureB);
                    if (newC) {
                        ContactFixture newContact = ContactUtils.getNewContact(fixtureA, fixtureB);
                        if (contactListener != null)
                            contactListener.preSolve(newContact);
                        data.addContact(newContact);
                        if (newContact.doCollision) {
                            fixtureA.replace(fixtureB, newContact);
                            fixtureA.calculNormal(fixtureB, newContact);
                        }
                        if (newContact.doCollision) {
                            contactForces.computeContact(newContact);
                        }
                        if (contactListener != null && newContact.callNextMethods)
                            contactListener.beginContact(newContact);
                        if (newContact.doCollision) {
                            contactForces.applyResult(newContact);
                        }

                    }
                }
            }
        }
    }
}
