package com.nzt.box.world.data;

import com.badlogic.gdx.utils.ObjectMap;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;

public class MapContact {

    public ObjectMap<Body, ObjectMap<Body, ContactFixture>> mapContacts;

    private ContactFixture getContact(Body bodyA, Body bodyB) {
        return mapContacts.get(bodyA).get(bodyB);
    }

    private ObjectMap<Body, ContactFixture> getContacts(Body bodyA) {
        return mapContacts.get(bodyA);
    }
}
