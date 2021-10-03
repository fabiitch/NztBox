package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.box.shape.BodyShape;

public class Fixture<S extends BodyShape> {

    public Body body;
    public S bodyShape;
    public Object userData;
    public Array<ContactFixture> contacts = new Array();

    public Fixture(S bodyShape) {
        this.bodyShape = bodyShape;
    }

    public void changeBodyPosition(float x, float y) {
        bodyShape.changeBodyPosition(x, y);
    }

    public void changeBodyPosition(Vector2 position) {
        bodyShape.changeBodyPosition(position);
    }

    public ContactFixture hasContact(Fixture fixtureB) {
        for (int i = 0, n = contacts.size; i < n; i++) {
            ContactFixture contactFixture = contacts.get(i);
            if (contactFixture.hasFixtures(this, fixtureB)) {
                return contactFixture;
            }
        }
        return null;
    }

    public boolean testContact(Fixture fixtureB) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        boolean b = fixtureB.bodyShape.testContact(contactVisitor);
        return b;
    }

    public void replace(Fixture fixtureB, ContactFixture contactFixture) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        fixtureB.bodyShape.replace(contactVisitor, contactFixture);
    }

    public void calculNormal(Fixture fixtureB, ContactFixture contactFixture) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        fixtureB.bodyShape.calculNormal(contactVisitor, contactFixture);
    }
}
