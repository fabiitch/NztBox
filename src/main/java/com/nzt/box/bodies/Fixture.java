package com.nzt.box.bodies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.shape.BodyShape;
import com.nzt.gdx.utils.arrays.GdxArrayUtils;

public class Fixture<S extends BodyShape> {
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

    public boolean testContact(Fixture fixtureB, ContactResolver contactResolver) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor(contactResolver);
        boolean b = fixtureB.bodyShape.testContact(contactVisitor);
        return b;
    }

    public void replace(Fixture fixtureB, ContactFixture contactFixture, ContactResolver contactResolver) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor(contactResolver);
        fixtureB.bodyShape.replace(contactVisitor, contactFixture);
    }

    public void calculCollisionData(Fixture fixtureB, ContactFixture contactFixture, ContactResolver contactResolver) {
        ShapeContact contactVisitor = bodyShape.getContactVisitor(contactResolver);
        fixtureB.bodyShape.calculCollisionData(contactVisitor, contactFixture);
    }

    public Array<Fixture<S>> getFixturesContact(Array<Fixture<S>> fixtureArray, boolean checkAlreadyPresent) {
        for (int i = 0, n = contacts.size; i < n; i++) {
            ContactFixture contactFixture = contacts.get(i);
            Fixture other = contactFixture.getOther(this);
            if (checkAlreadyPresent) GdxArrayUtils.addIfNotPresent(fixtureArray, other);
            else
                fixtureArray.add(other);
        }
        return fixtureArray;
    }
}
