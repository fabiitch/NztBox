package com.nzt.box.shape.contact.detector.impl;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.contact.detector.ShapeContact;

public class CircleContact implements ShapeContact {
    public Circle myCircle;

    private Vector2 tmp = new Vector2();

    @Override
    public boolean testContact(Circle circle) {
        float dst = tmp.set(circle.x, circle.y).dst(myCircle.x, myCircle.y);
        return dst <= myCircle.radius + circle.radius;
    }

    @Override
    public boolean testContact(Polygon polygon) {
        System.out.println("CircleContact contactPolygon");
        return false;
    }

}
