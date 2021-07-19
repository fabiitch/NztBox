package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorCirclePolygon;

public class PolygonContact implements ShapeContact {

    public Polygon myPolygon;
    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        return IntersectorCirclePolygon.circlePolygon(circle, myPolygon);
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return false;
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return Intersector.intersectPolygons(polygon, myPolygon, null);
    }

    @Override
    public void replace(Circle circle, ContactBody contactBody) {
//        Body bodyA = contactBody.fixtureA.body;
//        Vector3 velocity = bodyA.velocity;
//        tmp2.set(-velocity.x, -velocity.y);
//
//        Intersector.MinimumTranslationVector translationVector = IntersectorCirclePolygon.translationVector;
//        IntersectorCirclePolygon.circlePolygon(myCircle, polygon, translationVector);
//
//        tmp.set(myCircle.x, myCircle.y);
//        tmp2.setLength(translationVector.depth);
//        bodyA.setPosition(tmp.add(tmp2));
    }

    @Override
    public void replace(Rectangle rectangle, ContactBody contactBody) {
    }

    @Override
    public void replace(Polygon polygon, ContactBody contactBody) {
    }
}
