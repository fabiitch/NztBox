package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.math.quadtree.QuadTreeContainer;

public class WorldData {

    public Array<Body> bodies;

    public QuadTreeContainer quadTreeContainer;

    public int bodiesIdGenerator = 1;

    public WorldData() {
        super();
        this.bodies = new Array<>();
        this.quadTreeContainer = new QuadTreeContainer();
    }

    public void addBody(Body body) {
        if (bodiesIdGenerator == Integer.MAX_VALUE) {
            bodiesIdGenerator = 1;
        }
        body.id = bodiesIdGenerator++;

        body.dirtyPos = true;
        bodies.add(body);
        quadTreeContainer.addBody(body);
        body.updatePosition();
    }

    public Body getBody(int id) {
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (body.id == id)
                return body;
        }
        return null;
    }

    public Body getBody(Object userData) {
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (body.userData == userData)
                return body;
        }
        return null;
    }

    public void removeBody(Body body) {
        quadTreeContainer.removeBody(body);
        for (ContactBody contactBody : body.contactsBody) {
            for (ContactFixture contactFixture : contactBody.contactsFixture) {
                contactFixture.fixtureA.contacts.removeValue(contactFixture, true);
                contactFixture.fixtureB.contacts.removeValue(contactFixture, true);
            }
            if (contactBody.imBodyA(body)) {
                contactBody.bodyB.contactsBody.removeValue(contactBody, true);
            } else {
                contactBody.bodyA.contactsBody.removeValue(contactBody, true);
            }
        }
        body.contactsBody.clear();
        bodies.removeValue(body, true); //remove body
    }

    public void addContact(ContactFixture contactFixture) {
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;

        fixtureA.contacts.add(contactFixture);
        fixtureB.contacts.add(contactFixture);

        Body bodyA = fixtureA.body;
        Body bodyB = fixtureB.body;
        ContactBody contactBody = bodyA.hasContact(bodyB);
        if (contactBody == null)
            contactBody = ContactBody.get(fixtureA.body, fixtureB.body);
        contactBody.contactsFixture.add(contactFixture);

        fixtureA.body.contactsBody.add(contactBody);
        fixtureB.body.contactsBody.add(contactBody);
    }


    public void endContact(ContactFixture contactFixture) {
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;
        Body bodyA = fixtureA.body;
        Body bodyB = fixtureB.body;
        fixtureA.contacts.removeValue(contactFixture, true);
        fixtureB.contacts.removeValue(contactFixture, true);

        bodyA.endContact(bodyB, contactFixture);
        bodyB.endContact(bodyA, contactFixture);
    }


    public ContactBody getContact(Body bodyA, Body bodyB) {
        return bodyA.hasContact(bodyB);
    }

    public ContactFixture getContact(Fixture<?> fixtureA, Fixture<?> fixtureB) {
        return fixtureA.hasContact(fixtureB);
    }
}
