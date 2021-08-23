package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;

public class Fixture<S extends BodyShape> {
    public S bodyShape;
    public Body body;
    public Array<ContactBody> contacts = new Array();

    public Fixture(S bodyShape) {
        this.bodyShape = bodyShape;
    }

    public void changeBodyPosition(float x, float y) {
        bodyShape.changeBodyPosition(x, y);
    }

    public void changeBodyPosition(Vector2 position) {
        bodyShape.changeBodyPosition(position);
    }


    public ContactBody hasContact(Fixture fixtureB) {
        for (int i = 0, n = contacts.size; i < n; i++) {
            ContactBody contactBody = contacts.get(i);
            if (contactBody.hasFixtures(this, fixtureB)) {
                return contactBody;
            }
        }
        return null;
    }

    public boolean testContact(Fixture fixtureB) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        boolean b = fixtureB.bodyShape.testContact(contactVisitor);
        return b;
    }

    public void replace(Fixture fixtureB, ContactBody contactBody) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        fixtureB.bodyShape.replace(contactVisitor, contactBody);
    }

    public void rebound(Fixture fixtureB, ContactBody contactBody) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        fixtureB.bodyShape.rebound(contactVisitor, contactBody);
    }
}
