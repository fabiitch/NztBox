package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorCircle;
import com.nzt.gdx.math.shapes.Segment;
import com.nzt.gdx.math.shapes.utils.CircleUtils;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.math.vectors.V2;

public class ContactCircle implements ShapeContact {

    public Circle myCircle;

    private final Vector2 tmp = new Vector2();
    private final Vector2 tmp2 = new Vector2();
    private final Vector2 tmp3 = new Vector2();
    private final Segment tmpSegment = new Segment();

    @Override
    public boolean testContact(Circle circle) {
        float dst2 = tmp.set(circle.x, circle.y).dst2(myCircle.x, myCircle.y);
        return dst2 <= (myCircle.radius + circle.radius) * (myCircle.radius + circle.radius);
    }

    @Override
    public void replace(Circle circle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        Vector2 circleACenter = CircleUtils.getCenter(myCircle, tmp);
        float dst = myCircle.radius + circle.radius - tmp.dst(circle.x, circle.y);

        Vector2 circleBCenter = CircleUtils.getCenter(circle, tmp2);
        Vector2 dirToA = V2.directionTo(circleBCenter, circleACenter, tmp3);
        if (dirToA.isZero())
            dirToA.setToRandomDirection();
        dirToA.setLength(dst);

        Vector2 add = circleACenter.add(dirToA);
        bodyA.setPosition(add);
    }

    @Override
    public void calculCollisionData(Circle circle, ContactFixture contactFixture) {
        Vector2 half = V2.middle(circle.x, circle.y, myCircle.x, myCircle.y, tmp);
        Vector2 tangent = CircleUtils.getTangent(circle, half, tmp2);
        V2.getNormal(tangent, contactFixture.collisionData.normal);

        CircleUtils.getCenter(myCircle, tmp);
        CircleUtils.getCenter(circle, tmp2);
        Vector2 dirToB = V2.directionTo(tmp, tmp2, tmp3);
        if (dirToB.isZero()) {
            contactFixture.collisionData.collisionPoint.set(half);
        } else {
            Vector2 contactPoint = CircleUtils.posWithAngleRad(myCircle, dirToB.angleRad(), tmp);
            contactFixture.collisionData.collisionPoint.set(contactPoint);
        }
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return Intersector.overlaps(myCircle, rectangle);
    }


    @Override
    public void replace(Rectangle rectangle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        IntersectorCircle.replaceFromRectangle(myCircle, rectangle, tmp);

        CircleUtils.getCenter(myCircle, tmp2);
        tmp2.add(tmp);
        bodyA.setPosition(tmp2);
    }

    @Override
    public void calculCollisionData(Rectangle rectangle, ContactFixture contactFixture) {
        Vector2 circleCenter = CircleUtils.getCenter(myCircle, tmp);
        Segment segmentRect = RectangleUtils.closestEdge(rectangle, circleCenter, tmpSegment);
        Vector2 normal = segmentRect.getNormal(circleCenter, contactFixture.collisionData.normal);

        Vector2 contactPoint = circleCenter.sub(tmp2.set(normal).setLength(myCircle.radius));
        contactFixture.collisionData.collisionPoint.set(contactPoint);
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return IntersectorCircle.polygon(myCircle, polygon);
    }


    @Override
    public void replace(Polygon polygon, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;

        IntersectorCircle.replaceFromPolygon(myCircle, polygon, tmp);
        CircleUtils.getCenter(myCircle, tmp2);
        tmp2.add(tmp);
        bodyA.setPosition(tmp2);

    }

    @Override
    public void calculCollisionData(Polygon polygon, ContactFixture contactFixture) {
        Vector2 circleCenter = CircleUtils.getCenter(myCircle, tmp);

        Segment closestEdge = PolygonUtils.getClosestEdge(polygon, circleCenter, tmpSegment);
        Vector2 normal = closestEdge.getNormal(circleCenter, contactFixture.collisionData.normal);

        Vector2 contactPoint = circleCenter.sub(tmp2.set(normal).setLength(myCircle.radius));
        contactFixture.collisionData.collisionPoint.set(contactPoint);
    }

}
