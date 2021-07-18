package com.nzt.box.contact.detector.impl;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;

public class PolygonContact implements ShapeContact {

    public Polygon myPolygon;

    @Override
    public boolean testContact(Circle circle) {
        return false;
    }

    @Override
    public boolean testContact(Rectangle rectangle) {
        return false;
    }

    @Override
    public boolean testContact(Polygon polygon) {
        return false;
    }

    @Override
    public void replace(Circle circle, ContactBody contactBody) {
    }

    @Override
    public void replace(Rectangle rectangle, ContactBody contactBody) {
    }

    @Override
    public void replace(Polygon polygon, ContactBody contactBody) {
    }
}
