package com.nzt.box;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.contact.ContactBody;
import com.nzt.box.shape.contact.listener.ContactListener;

public class World {

    public SnapshotArray<Body> bodies;
    public ContactListener contactListener;
    private Array<Fixture> arrayFixturesTmp = new Array<>();
    public Array<Body> tmpArrayBody = new Array<>();

    public World() {
        this.bodies = new SnapshotArray<>();
    }

    public void step(float dt) {
        bodies.begin();
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (body.bodyType != BodyType.Static) {
                body.move(dt);
                checkCollision(body);
            }
        }
        bodies.end();
    }

    public void addBody(Body body) {

    }

    public void checkCollision(Body body) {
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body bodyTest = bodies.get(i);
            if (body != bodyTest) {
                testContact(body, bodyTest);
            }
        }
    }

    public void testContact(Body bodyA, Body bodyB) {
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            for (int j = 0, m = bodyB.fixtures.size; j < m; j++) {
                Fixture fixtureA = bodyA.fixtures.get(i);
                Fixture fixtureB = bodyB.fixtures.get(i);
                ContactBody contactBody = fixtureA.hasContact(fixtureB);
                if (contactBody != null) { //already contact
                    boolean retry = contactBody.retry();
                    if (retry) {
                        if (contactBody.tickEveryStep) {
                            contactListener.continusContact(contactBody);
                        }
                    } else {
                        contactListener.endContact(contactBody);
                        fixtureA.contacts.removeValue(contactBody, true);
                        fixtureB.contacts.removeValue(contactBody, true);
                        Pools.free(contactBody);
                    }
                } else {
                    boolean hasContact = fixtureA.testContact(fixtureB);
                    contactBody = ContactBody.get(fixtureA, fixtureB);
                    if (hasContact) {
                        fixtureA.contacts.add(contactBody);
                        fixtureB.contacts.add(contactBody);
                        if (contactListener != null)
                            contactListener.beginContact(contactBody);
                    }
                }


            }
        }
    }

}
