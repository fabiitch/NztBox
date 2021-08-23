package com.nzt.box.test.api.contact;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.detector.impl.ContactRectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactRectangleTest implements ContactTest {

    private static ContactRectangle contactRectangle;

    @BeforeEach
    @Override
    public void init() {
        contactRectangle = new ContactRectangle();
        Rectangle rectangle = new Rectangle(0, 0, 100, 50);
        contactRectangle.myRectangle = rectangle;
    }

    @Test
    @Override
    public void contactCircle() {
        Circle circle = new Circle(0, 0, 50);
        Assertions.assertTrue(contactRectangle.testContact(circle));

        circle.setPosition(151, 0);
        Assertions.assertFalse(contactRectangle.testContact(circle));

        circle.setPosition(0, 101);
        Assertions.assertFalse(contactRectangle.testContact(circle));
    }

    @Test
    @Override
    public void contactRectangle() {
        Rectangle rect = new Rectangle(0, 0, 20, 100);
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(99.9f, 0);
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(121, 0);
        Assertions.assertFalse(contactRectangle.testContact(rect));

        rect.setPosition(0, -70);
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(0, -100);
        Assertions.assertFalse(contactRectangle.testContact(rect));
    }

    @Test
    @Override
    public void contactPolygon() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0};
        Polygon polygon = new Polygon(vertices);

        Assertions.assertTrue(contactRectangle.testContact(polygon));

        polygon.setPosition(101, 50);
        Assertions.assertFalse(contactRectangle.testContact(polygon));

    }
}
