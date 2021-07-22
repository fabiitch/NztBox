package com.nzt.box.contact.detector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.ContactBody;

public interface ShapeContact {

    boolean testContact(Circle circle);

    void replace(Circle circle, ContactBody contactBody);

    boolean testContact(Rectangle rectangle);

    void replace(Rectangle rectangle, ContactBody contactBody);

    boolean testContact(Polygon polygon);

    void replace(Polygon polygon, ContactBody contactBody);


}
