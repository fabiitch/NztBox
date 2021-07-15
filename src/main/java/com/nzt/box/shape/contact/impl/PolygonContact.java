package com.nzt.box.shape.contact.impl;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.contact.ShapeContactVisitor;

public class PolygonContact implements ShapeContactVisitor {

    public Polygon myPolygon;

    @Override
    public boolean testContact(Circle circle) {
        System.out.println("PolygonContact contactCircle");
        return false;
    }

    @Override
    public boolean testContact(Polygon polygon) {
        System.out.println("PolygonContact contactPolygon");
        return false;
    }
}
