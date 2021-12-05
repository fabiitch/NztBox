package com.nzt.box.test.unit.intersectors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.detector.impl.ContactPolygon;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactPolygonTest implements ContactShapeTest {

    private static ContactPolygon contactPolygon;

    @BeforeEach
    @Override
    public void init() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0};
        Polygon polygon = new Polygon(vertices);
        PolygonUtils.ensureClockWise(polygon);
        contactPolygon = new ContactPolygon();
        contactPolygon.myPolygon = polygon;
    }

    @Test
    @Override
    public void contactCircle() {
        Circle circle = new Circle(0, 0, 50);
        Assertions.assertTrue(contactPolygon.testContact(circle));

        circle.setPosition(149.99999f, 0);//stick before
        Assertions.assertTrue(contactPolygon.testContact(circle));

        circle.setPosition(150, 0); //stick
        Assertions.assertTrue(contactPolygon.testContact(circle));

        circle.setPosition(150.001f, 0);//stick after
        Assertions.assertFalse(contactPolygon.testContact(circle));

        circle.setPosition(151, 0);
        Assertions.assertFalse(contactPolygon.testContact(circle));
    }

    @Test
    @Override
    public void contactRectangle() {
        Rectangle rect = new Rectangle(0, 0, 20, 100);
        Assertions.assertTrue(contactPolygon.testContact(rect));

        rect.setPosition(50, 0);
        Assertions.assertTrue(contactPolygon.testContact(rect));

        rect.setPosition(99.9999f, 0);//stick before
        Assertions.assertTrue(contactPolygon.testContact(rect));
//
        rect.setPosition(100,0 );//stick
        Assertions.assertTrue(contactPolygon.testContact(rect));

        rect.setPosition(100.001f,0 );//stick after
        Assertions.assertFalse(contactPolygon.testContact(rect));

        rect.setPosition(0, 121);
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
        PolygonUtils.ensureClockWise(polygon);

        Assertions.assertTrue(contactPolygon.testContact(polygon));

        polygon.setPosition(50, 0);
        Assertions.assertTrue(contactPolygon.testContact(polygon));

        polygon.setPosition(99.9999f, 0);//stick before
        Assertions.assertTrue(contactPolygon.testContact(polygon));

        polygon.setPosition(100, 0);//stick
        Assertions.assertTrue(contactPolygon.testContact(polygon));

        polygon.setPosition(100.0001f, 0);//stick after
        Assertions.assertFalse(contactPolygon.testContact(polygon));

        polygon.setPosition(101, 0);
        Assertions.assertFalse(contactPolygon.testContact(polygon));
    }
}
