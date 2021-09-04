package com.nzt.box.contact.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.bodies.Body;

public class ContactBody implements Pool.Poolable {
    public Body bodyA;
    public Body bodyB;
    public Array<ContactFixture> contacts;

    public ContactBody() {
        contacts = new Array<>();
    }

    public boolean imBodyA(Body body) {
        return bodyA == body;
    }

    public String debug() {
        return "ContactBody=[BodyA=" + bodyA.userData + "&& BodyB=" + bodyB.userData + " ==> " + contacts.size + " contacts]";
    }

    @Override
    public void reset() {
        bodyA = null;
        bodyB = null;
        contacts.clear();
    }

    public static ContactBody get(Body bodyA, Body bodyB) {
        ContactBody contactBody = Pools.obtain(ContactBody.class);
        contactBody.bodyA = bodyA;
        contactBody.bodyB = bodyB;
        return contactBody;
    }
}
