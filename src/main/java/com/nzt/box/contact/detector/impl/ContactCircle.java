package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.intersectors.IntersectorCircle;
import com.nzt.gdx.math.shapes.Segment;
import com.nzt.gdx.math.shapes.utils.CircleUtils;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.math.vectors.V2;

public class ContactCircle implements ShapeContact {

    public Circle myCircle;

    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();
    private Vector2 tmp3 = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        float dst = tmp.set(circle.x, circle.y).dst(myCircle.x, myCircle.y);
        return dst <= myCircle.radius + circle.radius;
    }

    @Override
    public void replace(Circle circle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        CircleUtils.getCenter(myCircle, tmp);

        float dst = myCircle.radius + circle.radius - tmp.dst(circle.x, circle.y);

        CircleUtils.getCenter(circle, tmp2);
        tmp3 = V2.directionTo(tmp2, tmp, tmp3);
        tmp3.setLength(dst);

        Vector2 add = tmp.add(tmp3);
        bodyA.setPosition(add);
    }

    @Override
    public void rebound(Circle circle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        Vector2 half = V2.middle(circle.x, circle.y, myCircle.x, myCircle.y, new Vector2());
        Vector2 tangent = CircleUtils.getTangent(circle, half, new Vector2());
        float angleIncidence = AngleUtils.angleIncidence(tangent, bodyA.getVelocity(tmp));
        bodyA.setVelocity(tmp.setAngleDeg(AngleUtils.incidenceToReflexion(angleIncidence)));
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
    public void rebound(Rectangle rectangle, ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        Vector2 circleCenter = CircleUtils.getCenter(myCircle, tmp);
        Segment segment = new Segment();
        RectangleUtils.getNearestSegment(rectangle, circleCenter, segment);
        float angleIncidence = AngleUtils.angleIncidence(segment, bodyA.getVelocity(tmp2));
        bodyA.setVelocity(tmp.setAngleDeg(AngleUtils.incidenceToReflexion(angleIncidence)));
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
    public void rebound(Polygon polygon, ContactFixture contactFixture) {
        Vector2 circleCenter = CircleUtils.getCenter(myCircle, new Vector2());

        Body bodyA = contactFixture.fixtureA.body;
        Segment segment = new Segment();
        PolygonUtils.getNearestSegment(polygon, circleCenter, segment);
        float angleIncidence = AngleUtils.angleIncidence(segment, bodyA.getVelocity(tmp));
        bodyA.setVelocity(tmp.setAngleDeg(AngleUtils.incidenceToReflexion(angleIncidence)));
    }

}
