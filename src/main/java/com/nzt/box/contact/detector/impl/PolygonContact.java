package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorCircle;
import com.nzt.gdx.math.intersectors.IntersectorPolygon;
import com.nzt.gdx.math.vectors.V2;

public class PolygonContact implements ShapeContact {

    public Polygon myPolygon;
    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        return IntersectorPolygon.circle(circle, myPolygon);
    }

    @Override
    public void replace(Circle circle, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;

        IntersectorCircle.replaceCirclePolygon(circle, myPolygon, tmp);

        tmp2.set(myPolygon.getX(), myPolygon.getY());

        V2.inv(tmp);

        bodyA.setPosition(tmp2.add(tmp));
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return IntersectorPolygon.rectangle(myPolygon, rectangle);
    }

    @Override
    public void replace(Rectangle rectangle, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;

        boolean overlaps = IntersectorPolygon.rectangle(myPolygon, rectangle, IntersectorPolygon.tmpTranslationVector);
        if (overlaps) {
            tmp.set(myPolygon.getX(), myPolygon.getY());
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp.add(tmp2));
        }

    }

    @Override
    public boolean testContact(Polygon polygon) {
        return Intersector.intersectPolygons(polygon, myPolygon, null);
    }

    @Override
    public void replace(Polygon polygon, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;

        boolean overlaps = IntersectorPolygon.polygons(myPolygon, polygon, IntersectorPolygon.tmpTranslationVector);
        if (overlaps) {
            tmp.set(myPolygon.getX(), myPolygon.getY());
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp.add(tmp2));
        }
    }
}
