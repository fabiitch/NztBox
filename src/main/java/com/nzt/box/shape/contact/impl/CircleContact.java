package com.nzt.box.shape.contact.impl;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.contact.ShapeContactVisitor;

public class CircleContact implements ShapeContactVisitor {
    public Circle myCircle;

    @Override
    public boolean testContact(Circle circle) {
        System.out.println("CircleContact contactCircle");
        return false;
    }

    @Override
    public boolean testContact(Polygon polygon) {
        System.out.println("CircleContact contactPolygon");
        return false;
    }

}
