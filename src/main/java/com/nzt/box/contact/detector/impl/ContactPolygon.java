package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorCircle;
import com.nzt.gdx.math.intersectors.IntersectorPolygon;
import com.nzt.gdx.math.shapes.Segment;
import com.nzt.gdx.math.shapes.utils.CircleUtils;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;
import com.nzt.gdx.math.vectors.V2;

public class ContactPolygon implements ShapeContact {

    public Polygon myPolygon;
    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        return IntersectorPolygon.circle(circle, myPolygon);
    }

    @Override
    public void replace(Circle circle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        IntersectorCircle.replaceFromPolygon(circle, myPolygon, tmp);
        Vector2 polygonPos = PolygonUtils.getPos(myPolygon, tmp2);
        V2.inv(tmp);
        bodyA.setPosition(polygonPos.add(tmp));
    }

    @Override
    public void calculCollisionData(Circle circle, ContactFixture contactFixture) {
        Vector2 circleCenter = CircleUtils.getCenter(circle, tmp);
        Segment closestEdge = PolygonUtils.getClosestEdge(myPolygon, circleCenter, new Segment());

        Vector2 contactPoint = closestEdge.closestPoint(circleCenter, tmp2);
        Vector2 tangent = CircleUtils.getTangent(circle, contactPoint, new Vector2());
        V2.getNormal(tangent, contactFixture.collisionData.normal);

        contactFixture.collisionData.collisionPoint.set(contactPoint);
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return IntersectorPolygon.rectangle(myPolygon, rectangle);
    }

    @Override
    public void replace(Rectangle rectangle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        boolean overlaps = IntersectorPolygon.rectangle(myPolygon, rectangle, IntersectorPolygon.tmpTranslationVector);
        if (overlaps) {
            tmp.set(myPolygon.getX(), myPolygon.getY());
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp.add(tmp2));
        }
    }

    @Override
    public void calculCollisionData(Rectangle rectangle, ContactFixture contactFixture) {
        boolean overlaps = IntersectorPolygon.rectangle(myPolygon, rectangle, IntersectorPolygon.tmpTranslationVector);//TODO group avec replace
        if (overlaps)
            contactFixture.collisionData.normal.set(IntersectorPolygon.tmpTranslationVector.normal);
        else {
            throw new GdxRuntimeException("Contact poly/rect, dont find intersection");
        }
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return IntersectorPolygon.polygons(polygon, myPolygon, null);
    }

    @Override
    public void replace(Polygon polygon, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;

        boolean overlaps = IntersectorPolygon.polygons(myPolygon, polygon, IntersectorPolygon.tmpTranslationVector);
        if (overlaps) {
            Vector2 posPolygon = PolygonUtils.getPos(myPolygon, tmp);
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(posPolygon.add(tmp2));
        }
    }

    private Polygon polygonOverlaps = new Polygon();

    @Override
    public void calculCollisionData(Polygon polygon, ContactFixture contactFixture) {
        boolean overlaps = IntersectorPolygon.polygons(myPolygon, polygon, IntersectorPolygon.tmpTranslationVector);
        if (overlaps)
            contactFixture.collisionData.normal.set(IntersectorPolygon.tmpTranslationVector.normal);

        boolean overlaps2 = Intersector.intersectPolygons(myPolygon, polygon, polygonOverlaps);
        if (polygonOverlaps.getTransformedVertices().length == 0) {//TODO
            System.out.println("no poly");
        }
        if (overlaps2 && polygonOverlaps.getTransformedVertices().length > 0) {
            PolygonUtils.getCenter(polygonOverlaps, contactFixture.collisionData.collisionPoint);
        } else {
            System.out.println("toot");
        }
    }
}
