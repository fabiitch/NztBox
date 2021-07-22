package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorPolygon;
import com.nzt.gdx.math.intersectors.IntersectorRectangle;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.math.vectors.V2;


public class RectangleContact implements ShapeContact {
    public Rectangle myRectangle;

    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();
    private Vector2 tmp3 = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        return Intersector.overlaps(circle, myRectangle);
    }

    @Override
    public void replace(Circle circle, ContactBody contactBody) {
        System.out.println("passe");
        Body bodyA = contactBody.fixtureA.body;
        Vector2 circleCenter = tmp.set(circle.x, circle.y);
        Vector2 rectCenter = RectangleUtils.getCenter(myRectangle, tmp2);
        Vector2 nearestPoint = RectangleUtils.getNearestPoint(myRectangle, circleCenter, tmp3);

        float dst = nearestPoint.dst(circleCenter);
        if (dst < circle.radius) {
            float dstToGo = circle.radius - dst;
            Vector2 direction = V2.directionTo(circleCenter, nearestPoint, tmp3);
            System.out.println(direction);

            if (circle.contains(rectCenter)) {
                if (direction.epsilonEquals(Vector2.X))
                    dstToGo += myRectangle.width;
                if (direction.epsilonEquals(Vector2.Y))
                    dstToGo += myRectangle.height;

            }
            direction.scl(dstToGo);
            rectCenter.add(direction);
            bodyA.setPosition(rectCenter);

        }
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return rectangle.overlaps(myRectangle);
    }


    @Override
    public void replace(Rectangle rectangle, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;

        Intersector.MinimumTranslationVector translationVector = IntersectorPolygon.tmpTranslationVector;
        boolean overlaps = IntersectorRectangle.rectangles(myRectangle, rectangle, translationVector);
        if (overlaps) {
            tmp.set(RectangleUtils.getCenter(myRectangle, tmp));
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp.add(tmp2));
        }
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return IntersectorPolygon.rectangle(polygon, myRectangle);
    }

    @Override
    public void replace(Polygon polygon, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;

        Intersector.MinimumTranslationVector translationVector = IntersectorPolygon.tmpTranslationVector;
        boolean overlaps = IntersectorRectangle.polygon(myRectangle, polygon, translationVector);
        if (overlaps) {
            tmp.set(RectangleUtils.getCenter(myRectangle, tmp));
            tmp2.set(IntersectorPolygon.tmpTranslationVector.normal).setLength(IntersectorPolygon.tmpTranslationVector.depth);
            bodyA.setPosition(tmp.add(tmp2));
        }
    }
}
