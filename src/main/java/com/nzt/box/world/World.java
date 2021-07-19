package com.nzt.box.world;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.listener.ContactListener;

public class World {

    protected WorldHelper helper;
    public SnapshotArray<Body> bodies;
    public ContactListener contactListener;

    public World() {
        helper = new WorldHelper();
        this.bodies = new SnapshotArray<>();
    }

    public void step(float dt) {
        bodies.begin();
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            boolean move = body.move(dt);
            if (move)
                checkCollision(body);
        }
        bodies.end();
    }

    public void addBody(Body body) {
        this.helper.addBody(body);
        this.bodies.add(body);
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
        first:
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            for (int j = 0, m = bodyB.fixtures.size; j < m; j++) {
                Fixture fixtureA = bodyA.fixtures.get(i);
                Fixture fixtureB = bodyB.fixtures.get(j);
                ContactBody contactBody = fixtureA.hasContact(fixtureB);
                if (contactBody != null) { //already contact
                    boolean retry = contactBody.retry();
                    if (retry) {
                        if (contactBody.tickEveryStep) {
                            contactListener.continusContact(contactBody);
                        }
                        if (BoxUtils.isContactBlock(bodyA, bodyB)) {
                            fixtureA.replace(fixtureB, contactBody);
                        }
                        break first;
                    } else {
                        contactListener.endContact(contactBody);
                        fixtureA.contacts.removeValue(contactBody, true);
                        fixtureB.contacts.removeValue(contactBody, true);
                        Pools.free(contactBody);
                    }
                } else {
                    boolean newContact = fixtureA.testContact(fixtureB);
                    if (newContact) {
                        contactBody = ContactBody.get(fixtureA, fixtureB);
                        fixtureA.contacts.add(contactBody);
                        fixtureB.contacts.add(contactBody);

                        if (BoxUtils.isContactBlock(bodyA, bodyB)) {
                            fixtureA.replace(fixtureB, contactBody);
                        }
                        if (contactListener != null)
                            contactListener.beginContact(contactBody);
                        break first;
                    }
                }
            }
        }
    }

}
