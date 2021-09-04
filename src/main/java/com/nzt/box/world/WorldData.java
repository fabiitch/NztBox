package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IdentityMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.box.contact.data.ContactFixture;

public class WorldData {

    private World world;
    public Array<Body> bodies;
    private IdentityMap<Fixture<?>, IdentityMap<Fixture<?>, ContactFixture>> mapFixtureContacts;
    private IdentityMap<Body, IdentityMap<Body, ContactBody>> mapBodyContacts;
    public Array<Body> activeBodies;

    public WorldData(World world) {
        super();
        this.world = world;
        this.bodies = new Array<>();
        this.activeBodies = new Array<>();
        this.mapFixtureContacts = new IdentityMap<>();
        this.mapBodyContacts = new IdentityMap<>();
    }

    public void addBody(Body body) {
        body.dirty = true;
        bodies.add(body);
        body.updatePosition();
    }

    public void destroyBody(Body body) {
        for (int i = 0, n = body.fixtures.size; i < n; i++) {
            Fixture<?> fixture = body.fixtures.get(i);
            Array<ContactFixture> contacts = fixture.contacts;
            for (int j = 0, m = contacts.size; j < m; j++) {
                ContactFixture contactFixture = contacts.get(j);
                if (world.contactListener != null)
                    world.contactListener.endContact(contactFixture);
                if (contactFixture.imFixtureA(fixture)) {
                    mapFixtureContacts.get(contactFixture.fixtureB).remove(fixture);
                    contactFixture.fixtureB.contacts.removeValue(contactFixture, true);
                } else {
                    mapFixtureContacts.get(contactFixture.fixtureA).remove(fixture);
                    contactFixture.fixtureB.contacts.removeValue(contactFixture, true);
                }
            }
            fixture.contacts.clear();
            mapFixtureContacts.remove(fixture);
        }

        for (int i = 0, n = body.contacts.size; i < n; i++) {
            ContactBody contactBody = body.contacts.get(i);
            if (contactBody.imBodyA(body)) {
                contactBody.bodyB.contacts.removeValue(contactBody, true);
                mapBodyContacts.get(contactBody.bodyB).remove(contactBody.bodyA);
            } else {
                contactBody.bodyA.contacts.removeValue(contactBody, true);
                mapBodyContacts.get(contactBody.bodyA).remove(contactBody.bodyB);
            }
        }
        mapBodyContacts.remove(body);
        body.contacts.clear();
        bodies.removeValue(body, true); //remove body
    }

    public void addContact(ContactFixture contactFixture) {
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;
        IdentityMap<Fixture<?>, ContactFixture> mapFixtureA = mapFixtureContacts.get(fixtureA);
        IdentityMap<Fixture<?>, ContactFixture> mapFixtureB = mapFixtureContacts.get(fixtureB);
        if (mapFixtureA == null) {
            mapFixtureA = new IdentityMap<>();
            mapFixtureContacts.put(fixtureA, mapFixtureA);
        }
        if (mapFixtureB == null) {
            mapFixtureB = new IdentityMap<>();
            mapFixtureContacts.put(fixtureB, mapFixtureB);
        }
        mapFixtureA.put(fixtureB, contactFixture);
        mapFixtureB.put(fixtureA, contactFixture);

        contactFixture.fixtureA.contacts.add(contactFixture);
        contactFixture.fixtureB.contacts.add(contactFixture);

        //==================
        // bodies
        ContactBody contactBody = null;
        Body bodyA = fixtureA.body;
        Body bodyB = fixtureB.body;
        IdentityMap<Body, ContactBody> alreadyMapA = mapBodyContacts.get(bodyA);
        if (alreadyMapA != null) {
            contactBody = alreadyMapA.get(bodyB);
        }
        if(contactBody == null){
            contactBody = ContactBody.get(bodyA, bodyB);
        }
        contactBody.contacts.add(contactFixture);

        IdentityMap<Body, ContactBody> mapBodyA = mapBodyContacts.get(bodyA);
        IdentityMap<Body, ContactBody> mapBodyB = mapBodyContacts.get(bodyB);

        if (mapBodyA == null) {
            mapBodyA = new IdentityMap<>();
            mapBodyContacts.put(bodyA, mapBodyA);
        }
        if (mapBodyB == null) {
            mapBodyB = new IdentityMap<>();
            mapBodyContacts.put(bodyB, mapBodyB);
        }
        mapBodyA.put(bodyB, contactBody);
        mapBodyB.put(bodyA, contactBody);

        bodyA.contacts.add(contactBody);
        bodyB.contacts.add(contactBody);
    }

    public ContactBody getContact(Body bodyA, Body bodyB) {
        ObjectMap<Body, ContactBody> mapContactBody = mapBodyContacts.get(bodyA);
        if (mapContactBody == null) {
            return null;
        }
        return mapContactBody.get(bodyB);
    }

    public ContactFixture getContact(Fixture<?> fixtureA, Fixture<?> fixtureB) {
        ObjectMap<Fixture<?>, ContactFixture> mapContactFixture = mapFixtureContacts.get(fixtureA);
        if (mapContactFixture == null) {
            return null;
        }
        return mapFixtureContacts.get(fixtureA).get(fixtureB);
    }

    public void endContact(ContactFixture contactFixture) {
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;

        mapFixtureContacts.get(fixtureA).remove(fixtureB);
        mapFixtureContacts.get(fixtureB).remove(fixtureA);

        contactFixture.fixtureA.contacts.removeValue(contactFixture, true);
        contactFixture.fixtureB.contacts.removeValue(contactFixture, true);
    }


}
