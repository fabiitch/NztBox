package com.nzt.box.contact.detector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.data.ContactFixture;

public interface ShapeContact {

    boolean testContact(Circle circle);

    void replace(Circle circle, ContactFixture contactFixture);

    void rebound(Circle circle, ContactFixture contactFixture, float stepTime);

    boolean testContact(Rectangle rectangle);

    void replace(Rectangle rectangle, ContactFixture contactFixture);

    void rebound(Rectangle rectangle, ContactFixture contactFixture);

    boolean testContact(Polygon polygon);

    void replace(Polygon polygon, ContactFixture contactFixture);

    void rebound(Polygon polygon, ContactFixture contactFixture);
}
