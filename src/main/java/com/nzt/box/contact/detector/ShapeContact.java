package com.nzt.box.contact.detector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.ContactBody;

public interface ShapeContact {

    boolean testContact(Circle circle);

    boolean testContact(Rectangle rectangle);

    boolean testContact(Polygon polygon);

    void replace(Circle circle, ContactBody contactBody);

    void replace(Rectangle rectangle, ContactBody contactBody);

    void replace(Polygon polygon, ContactBody contactBody);
}
