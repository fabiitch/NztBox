package com.nzt.box.shape.contact.detector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;

public interface ShapeContact {

    boolean testContact(Circle circle);

    boolean testContact(Polygon polygon);
}
