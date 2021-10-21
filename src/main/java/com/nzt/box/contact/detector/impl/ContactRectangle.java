package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorCircle;
import com.nzt.gdx.math.intersectors.IntersectorPolygon;
import com.nzt.gdx.math.intersectors.IntersectorRectangle;
import com.nzt.gdx.math.shapes.Segment;
import com.nzt.gdx.math.shapes.utils.CircleUtils;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.math.vectors.V2;


public class ContactRectangle implements ShapeContact {
    public Rectangle myRectangle;

    private Vector2 tmp1 = new Vector2();
    private Vector2 tmp2 = new Vector2();
    private Vector2 tmp3 = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        return Intersector.overlaps(circle, myRectangle);
    }

    @Override
    public void replace(Circle circle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        IntersectorCircle.replaceFromRectangle(circle, myRectangle, tmp1);
        V2.inv(tmp1);
        RectangleUtils.getCenter(myRectangle, tmp2);
        tmp2.add(tmp1);
        bodyA.setPosition(tmp2);
    }

    @Override
    public void calculCollisionData(Circle circle, ContactFixture contactFixture) {
        Vector2 circleCenter = CircleUtils.getCenter(circle, tmp1);
        Segment nearestSegment = RectangleUtils.closestSegment(myRectangle, circleCenter, new Segment());

        Vector2 contactPoint = nearestSegment.closestPoint(circleCenter, tmp2);
        Vector2 tangent = CircleUtils.getTangent(circle, contactPoint, new Vector2());
        V2.getNormal(tangent, contactFixture.collisionData.normal);

        contactFixture.collisionData.collisionPoint.set(contactPoint);
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return rectangle.overlaps(myRectangle);
    }


    @Override
    public void replace(Rectangle rectangle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;

        Intersector.MinimumTranslationVector translationVector = IntersectorPolygon.tmpTranslationVector;
        boolean overlaps = IntersectorRectangle.rectangles(myRectangle, rectangle, translationVector);
        if (overlaps) {
            RectangleUtils.getCenter(myRectangle, tmp1);
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp1.add(tmp2));
        }
    }

    @Override
    public void calculCollisionData(Rectangle rectangle, ContactFixture contactFixture) {
        boolean overlaps = IntersectorRectangle.rectangles(myRectangle, rectangle, IntersectorPolygon.tmpTranslationVector);
        if (overlaps)
            contactFixture.collisionData.normal.set(IntersectorPolygon.tmpTranslationVector.normal);

        RectangleUtils.getCenter(myRectangle, tmp1);
        RectangleUtils.getCenter(rectangle, tmp2);
        V2.directionTo(tmp1, tmp2, tmp3);

        Rectangle result = new Rectangle();
        Intersector.intersectRectangles(myRectangle, rectangle, result);
        System.out.println("r="+result);

        contactFixture.collisionData.collisionPoint.set(tmp1);
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return IntersectorPolygon.rectangle(polygon, myRectangle);
    }

    @Override
    public void replace(Polygon polygon, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;

        Intersector.MinimumTranslationVector translationVector = IntersectorPolygon.tmpTranslationVector;
        boolean overlaps = IntersectorRectangle.polygon(myRectangle, polygon, translationVector);
        if (overlaps) {
            tmp1.set(RectangleUtils.getCenter(myRectangle, tmp1));
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp1.add(tmp2));
        }
    }

    @Override
    public void calculCollisionData(Polygon polygon, ContactFixture contactFixture) {
        boolean overlaps = IntersectorRectangle.polygon(myRectangle, polygon, IntersectorPolygon.tmpTranslationVector);
        if (overlaps)
            contactFixture.collisionData.normal.set(IntersectorPolygon.tmpTranslationVector.normal);
    }
}
