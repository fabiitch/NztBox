package com.nzt.box.world;

import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.box.contact.listener.ContactListener;

public class World {

	int iterations = 10;
	int maxStepTime = 1 / 80;

	public SnapshotArray<Body> bodies;
	public ContactListener contactListener;

	public WorldHelper helper;
	public WorldData data;

	public World() {
		this.helper = new WorldHelper(this);
		this.data = new WorldData(this);
		this.bodies = new SnapshotArray<>();
	}

	public void step(float dt) {
		float stepTime = dt / iterations;
		for (int nbIteraton = 0; nbIteraton < iterations; nbIteraton++) {
			bodies.begin();
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
			bodies.end();
		}
	}

	public void addBody(Body body) {
		this.helper.addBody(body);
		this.bodies.add(body);
		body.updatePosition();
	}

	public void checkCollision(Body body) {
		for (int i = 0, n = bodies.size; i < n; i++) {
			Body bodyTest = bodies.get(i);
			if (body != bodyTest && body.active) {
				testContact(body, bodyTest);
			}
		}
	}

	public void testContact(Body bodyA, Body bodyB) {
//        if (!ContactUtils.canContact(bodyA, bodyB)) {
//            return;
//        }
		for (int i = 0, n = bodyA.fixtures.size; i < n; i++) {
			Fixture<?> fixtureA = (Fixture) bodyA.fixtures.get(i);
//            if (!ContactUtils.canContact(bodyB, fixtureA)) {
//                continue;
//            }
			for (int j = 0, m = bodyB.fixtures.size; j < m; j++) {
				Fixture<?> fixtureB = (Fixture) bodyB.fixtures.get(j);
//                if (!ContactUtils.canContact(fixtureA, fixtureB)) {
//                    continue;
//                }
				ContactBody contactBody = fixtureA.hasContact(fixtureB);
				if (contactBody != null) { // already contact
					boolean retry = contactBody.retry();
					if (retry) {
						if (contactBody.tickEveryStep) {
							if (contactListener != null)
								contactListener.continusContact(contactBody);
						}
						if (BoxUtils.isContactBlock(bodyA, bodyB)) {
							fixtureA.replace(fixtureB, contactBody);
						}
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
						if (contactListener != null)
							contactListener.beginContact(contactBody);
						if (BoxUtils.isContactBlock(bodyA, bodyB)) {
							fixtureA.replace(fixtureB, contactBody);
							fixtureA.rebound(fixtureB, contactBody);
						}
					}
				}
			}
		}
	}

}