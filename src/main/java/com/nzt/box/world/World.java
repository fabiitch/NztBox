package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;
import com.nzt.box.contact.compute.ContactCompute;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;

public class World {

    public ContactListener contactListener;
    public ContactCompute contactCompute;
    public WorldHelper helper;
    public WorldData data;

    public float stepTime;
    private float accumulator = 0f;

    public boolean simulationRunning = true;

    /**
     * @param stepTime
     * @param activeData
     */
    public World(float stepTime, boolean activeData) {
        this.stepTime = stepTime;
        this.data = new WorldData(this);
        this.helper = new WorldHelper(this);
        this.contactCompute = new ContactCompute();
    }

    public World(boolean activeData) {
        this(1 / 300f, activeData);
    }

    public World() {
        this(1f / 60f / 8f, true);
    }

    public void addBody(Body body) {
        helper.addId(body);
        data.addBody(body);
    }

    public void removeBody(Body body) {
        data.removeBody(body);
    }

    public void step(float dt) {
        if (!simulationRunning)
            return;
        accumulator += dt;
        while (accumulator >= stepTime && simulationRunning) {
            iteration();
            accumulator -= stepTime;
        }
    }

    protected void iteration() {
        Array<Body> bodies = data.bodies;
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (!body.dirtyPos && (!body.active || body.bodyType == BodyType.Static))
                continue;
            boolean move = body.move(stepTime);
            if (move || body.dirtyPos) {
                checkCollisions(body);
            }
            body.dirtyPos = false;
        }
    }

//    void toot(Fixture fixtureA, Array<Fixture<?>> fixturesClose) {
//        for (int j = 0, m = fixturesClose.size; j < m; j++) {
//            Fixture<?> fixtureB = fixturesClose.get(j);
//            Body bodyB = fixtureB.body;
//            if (!ContactUtils.canContact(bodyA, bodyB)) {
//                continue;
//            }
//            ContactFixture hasContact = data.getContact(fixtureA, fixtureB);
//            if (hasContact != null) {
//                boolean retry = hasContact.retry();
//                if (retry) {
//                    if (hasContact.continueContact && contactListener != null)
//                        contactListener.continueContact(hasContact);
//                    if (hasContact.doCollision)
//                        fixtureA.replace(fixtureB, hasContact);
//                } else {
//                    if (contactListener != null && hasContact.callNextMethods)
//                        contactListener.endContact(hasContact);
//                    data.endContact(hasContact);
//                }
//            } else {
//                boolean fastCheck = ContactUtils.canContact(fixtureA, fixtureB);
//                if (!fastCheck)
//                    continue;
//                boolean newC = fixtureA.testContact(fixtureB);
//                if (newC) {
//                    ContactFixture newContact = ContactUtils.getNewContact(fixtureA, fixtureB);
//                    if (contactListener != null)
//                        contactListener.preSolve(newContact);
//                    data.addContact(newContact);
//
//                    if (newContact.doCollision)
//                        fixtureA.replace(fixtureB, newContact);
//
//                    if (newContact.calculCollisionData)
//                        fixtureA.calculCollisionData(fixtureB, newContact);
//                    if (newContact.doCollision)
//                        contactCompute.computeContact(newContact);
//                    if (contactListener != null && newContact.callNextMethods)
//                        contactListener.beginContact(newContact);
//                    if (newContact.doCollision)
//                        contactCompute.applyResult(newContact);
//                }
//            }
//        }
//    }

    protected void checkCollisions(Body bodyA) {
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            Fixture<?> fixtureA = bodyA.fixtures.get(i);
            if (!fixtureA.active)
                continue;
            Array<Fixture<?>> fixturesClose = fixtureA.quadTree.getValuesAndParents(new Array<>());

        }
    }
}
