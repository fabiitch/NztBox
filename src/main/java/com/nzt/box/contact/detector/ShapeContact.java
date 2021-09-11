package com.nzt.box.contact.detector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.data.ContactFixture;

public interface ShapeContact {

    boolean testContact(Circle circle);

    void replace(Circle circle, ContactFixture contactFixture);

    void calculNormal(Circle circle, ContactFixture contactFixture);

    boolean testContact(Rectangle rectangle);

    void replace(Rectangle rectangle, ContactFixture contactFixture);

    void calculNormal(Rectangle rectangle, ContactFixture contactFixture);

    boolean testContact(Polygon polygon);

    void replace(Polygon polygon, ContactFixture contactFixture);

    void calculNormal(Polygon polygon, ContactFixture contactFixture);
}
