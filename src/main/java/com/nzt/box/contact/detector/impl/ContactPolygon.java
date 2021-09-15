package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.AngleUtils;
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
    public void calculNormal(Circle circle, ContactFixture contactFixture) {
        Vector2 circleCenter = CircleUtils.getCenter(circle, tmp);
        Segment nearestSegment = PolygonUtils.getNearestSegment(myPolygon, circleCenter, new Segment());

        Vector2 contactPoint = nearestSegment.nearestPoint(circleCenter, tmp2);
        Vector2 tangent = CircleUtils.getTangent(circle, contactPoint, new Vector2());
        V2.getNormal(tangent, contactFixture.collisionData.normal);
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
    public void calculNormal(Rectangle rectangle, ContactFixture contactFixture) {
        boolean overlaps = IntersectorPolygon.rectangle(myPolygon, rectangle, IntersectorPolygon.tmpTranslationVector);//TODO group avec replace
        if (overlaps)
            contactFixture.collisionData.normal.set(IntersectorPolygon.tmpTranslationVector.normal);
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return Intersector.intersectPolygons(polygon, myPolygon, null);
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

    @Override
    public void calculNormal(Polygon polygon, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
//        Vector2 normal = V2.getNormal(IntersectorPolygon.tmpTranslationVector.normal, new Vector2());
//        float angleIncidence = AngleUtils.angleIncidence(normal, bodyA.getVelocity(tmp));
//        bodyA.setVelocity(tmp.setAngleDeg(AngleUtils.incidenceToReflexion(angleIncidence)));

        boolean overlaps = IntersectorPolygon.polygons(myPolygon, polygon, IntersectorPolygon.tmpTranslationVector);
        if (overlaps)
            contactFixture.collisionData.normal.set(IntersectorPolygon.tmpTranslationVector.normal);
    }
}
