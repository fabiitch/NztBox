package com.nzt.box.shape.contact;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;

public interface ShapeContactVisitor{

    boolean testContact( Circle circle);

    boolean testContact(Polygon polygon);
}
