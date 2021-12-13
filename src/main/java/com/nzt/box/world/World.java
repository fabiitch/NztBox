package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;
import com.nzt.box.contact.compute.ContactCompute;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.profiler.WorldProfiler;

public class World {
    public WorldProfiler profiler;
    public WorldData data;
    public final boolean activeProfiler;

    protected ContactListener contactListener;
    protected ContactCompute contactCompute;
    protected ContactResolver contactResolver;

    public float stepTime;
    protected float accumulator = 0f;
    public boolean simulationRunning = true;

    public World(float stepTime, boolean activeProfiler) {
        this.stepTime = stepTime;
        this.data = new WorldData();
        this.profiler = new WorldProfiler();
        this.contactCompute = new ContactCompute();
        this.contactResolver = new ContactResolver();
        this.activeProfiler = activeProfiler;
    }

    public World(boolean activeProfiler) {
        this(1f / 60f / 8f, activeProfiler);
    }

    public World() {
        this(1f / 60f / 8f, true);
    }

    public void addBody(Body body) {
        data.addBody(body);
    }

    public void removeBody(Body body) {
        data.removeBody(body);
    }

    public void step(float dt) {
        if (!simulationRunning)
            return;
        if (activeProfiler) profiler.startStep();
        accumulator += dt;
        while (accumulator >= stepTime && simulationRunning) {
            if (activeProfiler) profiler.startIteration();
            iteration();
            accumulator -= stepTime;
            if (activeProfiler) profiler.endIteration();
        }
        if (activeProfiler) profiler.endStep();
    }

    protected void iteration() {
        if (activeProfiler) profiler.startIteration();
        Array<Body> bodies = data.bodies;
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (!body.dirtyPos && (!body.active || body.bodyType == BodyType.Static))
                continue;
            moveBody(body);
        }
    }

    protected void moveBody(Body body) {
        if (activeProfiler) profiler.moveBody.inc();
        boolean move = body.move(stepTime);
        if (move || body.dirtyPos) {
            checkBodyCollisions(body);
            body.dirtyPos = false;
        }
    }

    protected void checkBodyCollisions(Body bodyA) {
        if (activeProfiler) profiler.checkBodyCollision.inc();
        for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
            Fixture fixtureA = bodyA.fixtures.get(i);
            if (!fixtureA.active)
                continue;
            checkFixtureCollision(bodyA, fixtureA);
        }
    }

    protected void checkFixtureCollision(Body bodyA, Fixture fixtureA) {
        if (activeProfiler) profiler.checkFixtureCollision.inc();
        Array<Fixture> fixturesClose = fixtureA.quadTree.getValuesAndParents(new Array<>());
        fixtureA.getFixturesContact(fixturesClose, true);
        for (int i2 = 0, n2 = fixturesClose.size; i2 < n2; i2++) {
            Fixture fixtureB = fixturesClose.get(i2);
            if (fixtureA == fixtureB || !ContactUtils.shouldTestContact(bodyA, fixtureB.body)) {
                continue;
            }
            fixtureTestCollision(fixtureA, fixtureB);
        }
    }


    protected void fixtureTestCollision(Fixture fixtureA, Fixture fixtureB) {
        if (activeProfiler) profiler.fixtureTestCollision.inc();

        ContactFixture hasContact = data.getContact(fixtureA, fixtureB);
        if (hasContact != null) {
            boolean retryTestContact = ContactUtils.fastCheck(fixtureA, fixtureB);
            if (retryTestContact) {
                if (activeProfiler) profiler.testContact.inc();
                retryTestContact = hasContact.retry(contactResolver);
            }
            if (retryTestContact) {
                if (hasContact.continueContact && contactListener != null)
                    contactListener.continueContact(hasContact);
                if (hasContact.doCollision) {
                    fixtureA.replace(fixtureB, contactResolver);
                    if (activeProfiler) profiler.replaceContact.inc();
                }
            } else {
                if (contactListener != null && hasContact.callNextMethods)
                    contactListener.endContact(hasContact);
                data.endContact(hasContact);
                if (activeProfiler) profiler.endContact.inc();
            }
        } else {
            boolean fastCheck = ContactUtils.fastCheck(fixtureA, fixtureB);
            if (!fastCheck)
                return;
            boolean newC = fixtureA.testContact(fixtureB, contactResolver);
            if (activeProfiler) profiler.testContact.inc();
            if (newC) {
                if (activeProfiler) profiler.beginContact.inc();
                ContactFixture newContact = ContactUtils.getNewContact(fixtureA, fixtureB);
                if (contactListener != null)
                    contactListener.preSolve(newContact);
                data.addContact(newContact);
                if (newContact.doCollision) {
                    fixtureA.replace(fixtureB, contactResolver);
                    if (activeProfiler) profiler.replaceContact.inc();
                }
                if (newContact.doCalculData) {
                    fixtureA.calculCollisionData(fixtureB, newContact, contactResolver);
                    if (activeProfiler) profiler.collisionData.inc();
                }
                if (newContact.doCollision) {
                    contactCompute.computeContact(newContact);
                    if (activeProfiler) profiler.computeContact.inc();
                }
                if (contactListener != null && newContact.callNextMethods)
                    contactListener.beginContact(newContact);
                if (newContact.doCollision)
                    contactCompute.applyResult(newContact);
            }
        }
    }


    public ContactListener getContactListener() {
        return contactListener;
    }

    public void setContactListener(ContactListener contactListener) {
        this.contactListener = contactListener;
    }

    public ContactCompute getContactCompute() {
        return contactCompute;
    }

    public void setContactCompute(ContactCompute contactCompute) {
        this.contactCompute = contactCompute;
    }

    public ContactResolver getContactResolver() {
        return contactResolver;
    }

    public void setContactResolver(ContactResolver contactResolver) {
        this.contactResolver = contactResolver;
    }
}
