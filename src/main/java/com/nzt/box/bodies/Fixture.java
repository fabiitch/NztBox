package com.nzt.box.bodies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.shape.BodyShape;

public class Fixture<S extends BodyShape> {

    public static int countTestContact = 0;

    public boolean active = true;
    public Body body;
    public S bodyShape;
    public Object userData;
    public Array<ContactFixture> contacts = new Array();
    public QuadTree quadTree;

    public Fixture(S bodyShape) {
        this.bodyShape = bodyShape;
    }

    public Rectangle getBoundingRectangle() {
        return this.bodyShape.boundingRect;
    }

    public Rectangle computeBoundingRect() {
        return this.bodyShape.computeBoundingRect();
    }

    public void setPosition(float x, float y, float rotation) {
        bodyShape.setPosition(x, y);
        bodyShape.setRotation(rotation);
        computeBoundingRect();
        if (this.quadTree != null)
            this.quadTree.container.moveFixture(this);
    }

    public void setRotation(float rotation) {
        bodyShape.setRotation(rotation);
        computeBoundingRect();
        if (this.quadTree != null)
            this.quadTree.container.moveFixture(this);
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
        countTestContact++;
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        boolean b = fixtureB.bodyShape.testContact(contactVisitor);
        return b;
    }

    public void replace(Fixture fixtureB, ContactFixture contactFixture) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        fixtureB.bodyShape.replace(contactVisitor, contactFixture);
    }

    public void calculCollisionData(Fixture fixtureB, ContactFixture contactFixture) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor();
        fixtureB.bodyShape.calculCollisionData(contactVisitor, contactFixture);
    }
}
