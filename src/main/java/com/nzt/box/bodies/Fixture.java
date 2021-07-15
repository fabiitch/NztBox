package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.contact.ContactResolver;
import com.nzt.box.shape.contact.ShapeContactVisitor;

public class Fixture<S extends BodyShape> {
    public S bodyShape;
    public Body body;

    public Fixture(S bodyShape) {
        this.bodyShape = bodyShape;
    }

    public void changeBodyPosition(float x, float y) {
        bodyShape.changeBodyPosition(x, y);
    }

    public void changeBodyPosition(Vector2 position) {
        bodyShape.changeBodyPosition(position);
    }

    public void testContact(S otherShape) {
        ShapeContactVisitor contactVisitor = bodyShape.getContactVisitor();
        bodyShape.testContact(contactVisitor);
//        bodyShape.testContact(otherShape);
    }
}
