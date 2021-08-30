package com.nzt.box.world;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;

import java.util.Objects;

public class WorldData {

    private World world;
    public Array<Body> bodies;
    private ObjectMap<Fixture<?>, ObjectMap<Fixture<?>, ContactFixture>> mapContacts;
    public Array<Body> activeBodies;

    public WorldData(World world) {
        super();
        this.world = world;
        this.bodies = new Array<>();
        this.activeBodies = new Array<>();
        this.mapContacts = new ObjectMap<>();
    }

    public void addBody(Body body) {
        body.dirty = true;
        bodies.add(body);
        body.updatePosition();
    }

    public void removeBody(Body body) {
        for (int i = 0, n = body.fixtures.size; i < n; i++) {
            Fixture<?> fixture = body.fixtures.get(i);
            Array<ContactFixture> contacts = fixture.contacts;
            for (int j = 0, m = contacts.size; j < m; j++) {
                ContactFixture contactFixture = contacts.get(j);
                if (contactFixture.imFixtureA(fixture)) {
                    mapContacts.get(contactFixture.fixtureB).remove(fixture);
                    contactFixture.fixtureB.contacts.removeValue(contactFixture, true);
                } else {
                    mapContacts.get(contactFixture.fixtureA).remove(fixture);
                    contactFixture.fixtureB.contacts.removeValue(contactFixture, true);
                }
            }
            mapContacts.remove(fixture);
        }
        bodies.removeValue(body, true); //remove body
    }

    public void addContact(ContactFixture contactFixture) {
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;
        ObjectMap<Fixture<?>, ContactFixture> mapA = mapContacts.get(fixtureA);
        ObjectMap<Fixture<?>, ContactFixture> mapB = mapContacts.get(fixtureB);
        if (mapA == null) {
            mapA = new ObjectMap<>();
            mapContacts.put(fixtureA, mapA);
        }
        if (mapB == null) {
            mapB = new ObjectMap<>();
            mapContacts.put(fixtureA, mapA);
        }
        mapA.put(fixtureB, contactFixture);
        mapB.put(fixtureA, contactFixture);

        contactFixture.fixtureA.contacts.add(contactFixture);
        contactFixture.fixtureB.contacts.add(contactFixture);
    }

    public ContactFixture getContact(Fixture<?> fixtureA, Fixture<?> fixtureB) {
        ObjectMap<Fixture<?>, ContactFixture> mapContactFixture = mapContacts.get(fixtureA);
        if (mapContactFixture == null) {
            mapContacts.put(fixtureB, new ObjectMap<>());
            return null;
        }
        return mapContacts.get(fixtureA).get(fixtureB);
    }

    public void endContact(ContactFixture contactFixture) {
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;

        mapContacts.get(fixtureA).remove(fixtureB);
        mapContacts.get(fixtureB).remove(fixtureA);
    }


}
