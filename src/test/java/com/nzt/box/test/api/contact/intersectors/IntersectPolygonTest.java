package com.nzt.box.test.api.contact.intersectors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.detector.impl.ContactPolygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntersectPolygonTest implements IntersectTest {

    private static ContactPolygon contactPolygon;

    @BeforeEach
    @Override
    public void init() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0};
        Polygon polygon = new Polygon(vertices);
        contactPolygon = new ContactPolygon();
        contactPolygon.myPolygon = polygon;
    }

    @Test
    @Override
    public void contactCircle() {
        Circle circle = new Circle(0, 0, 50);

        Assertions.assertTrue(contactPolygon.testContact(circle));

        circle.setPosition(151, 0);
        Assertions.assertFalse(contactPolygon.testContact(circle));
    }

    @Test
    @Override
    public void contactRectangle() {
        Rectangle rect = new Rectangle(0, 0, 20, 100);
        Assertions.assertTrue(contactPolygon.testContact(rect));

        rect.setPosition(99.9f, 0);
        Assertions.assertTrue(contactPolygon.testContact(rect));

        rect.setPosition(121, 0);
        Assertions.assertFalse(contactPolygon.testContact(rect));

        rect.setPosition(0, -70);
        Assertions.assertTrue(contactPolygon.testContact(rect));


        rect.setPosition(0, -100);
        Assertions.assertTrue(contactPolygon.testContact(rect));
        rect.setPosition(0, -101);
        Assertions.assertFalse(contactPolygon.testContact(rect));
    }

    @Test
    @Override
    public void contactPolygon() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0};
        Polygon polygon = new Polygon(vertices);

        Assertions.assertTrue(contactPolygon.testContact(polygon));

        polygon.setPosition(101, 50);
        Assertions.assertFalse(contactPolygon.testContact(polygon));
    }
}
