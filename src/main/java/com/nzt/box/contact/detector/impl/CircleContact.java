package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.math.intersectors.IntersectorCircle;
import com.nzt.gdx.math.shapes.utils.CircleUtils;
import com.nzt.gdx.math.vectors.V2;

public class CircleContact implements ShapeContact {

    public Circle myCircle;

    private Vector2 tmp = new Vector2();
    private Vector2 tmp2 = new Vector2();
    private Vector2 tmp3 = new Vector2();
    private Vector2 tmp4 = new Vector2();
    @Override
    public boolean testContact(Circle circle) {
        float dst = tmp.set(circle.x, circle.y).dst(myCircle.x, myCircle.y);
        return dst <= myCircle.radius + circle.radius;
    }

    @Override
    public void replace(Circle circle, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;
        CircleUtils.getCenter(myCircle, tmp);

        float dst = myCircle.radius + circle.radius - tmp.dst(circle.x, circle.y);

        CircleUtils.getCenter(circle, tmp2);
        tmp3 = V2.directionTo(tmp2, tmp, tmp3);
        tmp3.setLength(dst);

        Vector2 add = tmp.add(tmp3);
        bodyA.setPosition(add);

        Vector2 tangentRad = CircleUtils.getTangentRad(circle, tmp3.angleRad(), tmp4);


//        float angleReflexionRad = CircleUtils.getAngleReflexionRad(circle, tmp3.angleRad());

        bodyA.getVelocity(tmp);
//        tmp.setAngleRad(angleReflexionRad);
        bodyA.setVelocity(tmp);
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return Intersector.overlaps(myCircle, rectangle);
    }


    @Override
    public void replace(Rectangle rectangle, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;
        IntersectorCircle.replaceFromRectangle(myCircle, rectangle, tmp);

        CircleUtils.getCenter(myCircle, tmp2);
        tmp2.add(tmp);
        bodyA.setPosition(tmp2);
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return IntersectorCircle.polygon(myCircle, polygon);
    }


    @Override
    public void replace(Polygon polygon, ContactBody contactBody) {
        Body bodyA = contactBody.fixtureA.body;

        IntersectorCircle.replaceFromPolygon(myCircle, polygon, tmp);
        CircleUtils.getCenter(myCircle, tmp2);
        tmp2.add(tmp);
        bodyA.setPosition(tmp2);

    }

}
