package com.nzt.box.world;

import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.ContactUtils;
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
            if (move || body.dirty) {
                checkCollision(body);
            }
            body.dirty = false;
        }
        bodies.end();
    }

    public void addBody(Body body) {
        this.helper.addBody(body);
        this.bodies.add(body);
        body.updatePosition();
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
//        if (!ContactUtils.canContact(bodyA, bodyB)) {
//            return;
//        }
        first:
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            Fixture fixtureA = bodyA.fixtures.get(i);
//            if (!ContactUtils.canContact(bodyB, fixtureA)) {
//                continue;
//            }
            for (int j = 0, m = bodyB.fixtures.size; j < m; j++) {
                Fixture fixtureB = bodyB.fixtures.get(j);
//                if (!ContactUtils.canContact(fixtureA, fixtureB)) {
//                    continue;
//                }
                ContactBody contactBody = fixtureA.hasContact(fixtureB);
                if (contactBody != null) { //already contact
                    boolean retry = contactBody.retry();
                    if (retry) {
                        if (contactBody.tickEveryStep) {
                            if (contactListener != null)
                                contactListener.continusContact(contactBody);
                        }
                        if (BoxUtils.isContactBlock(bodyA, bodyB)) {
                            fixtureA.replace(fixtureB, contactBody);
                        }
                        break first;
                    } else {
                        if (contactListener != null)
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
