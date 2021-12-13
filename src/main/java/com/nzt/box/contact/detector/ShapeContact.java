package com.nzt.box.contact.detector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;

public interface ShapeContact {

    boolean testContact(Circle circle);

    void replace(Body bodyToReplace, Circle circle);

    void calculCollisionData(Circle circle, ContactFixture contactFixture);

    boolean testContact(Rectangle rectangle);

    void replace(Body bodyToReplace, Rectangle rectangle);

    void calculCollisionData(Rectangle rectangle, ContactFixture contactFixture);

    boolean testContact(Polygon polygon);

    void replace(Body bodyToReplace, Polygon polygon);

    void calculCollisionData(Polygon polygon, ContactFixture contactFixture);
}
