package com.nzt.box.test.unit.intersectors;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.detector.impl.ContactRectangle;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactRectangleTest implements ContactShapeTest {

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

        circle.setPosition(149.9999f, 0);//stick before
        Assertions.assertTrue(contactRectangle.testContact(circle));

        circle.setPosition(150, 0);//stick
        Assertions.assertTrue(contactRectangle.testContact(circle));

        circle.setPosition(150.001f, 0);//stick after
        Assertions.assertFalse(contactRectangle.testContact(circle));

        circle.setPosition(0, 101);
        Assertions.assertFalse(contactRectangle.testContact(circle));
    }

    @Test
    @Override
    public void contactRectangle() {
        Rectangle rect = new Rectangle(0, 0, 20, 100);
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(99.9999f, 0);//stick before
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(100f, 0);//stick
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(100.0001f, 0);//stick after
        Assertions.assertFalse(contactRectangle.testContact(rect));

        rect.setPosition(0, -70);
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(0, -100);
        Assertions.assertTrue(contactRectangle.testContact(rect));

        rect.setPosition(0, -100.0001f);
        Assertions.assertFalse(contactRectangle.testContact(rect));
    }

    @Test
    @Override
    public void contactPolygon() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0};
        Polygon polygon = new Polygon(vertices);
        PolygonUtils.ensureClockWise(polygon);

        Assertions.assertTrue(contactRectangle.testContact(polygon));

        polygon.setPosition(50, 50);
        Assertions.assertTrue(contactRectangle.testContact(polygon));

        polygon.setPosition(99.9999f, 50);//stick before
        Assertions.assertTrue(contactRectangle.testContact(polygon));

        polygon.setPosition(100, 50);//stick
        Assertions.assertTrue(contactRectangle.testContact(polygon));

        polygon.setPosition(100.001f, 50);//stick after
        Assertions.assertFalse(contactRectangle.testContact(polygon));

        polygon.setPosition(101, 50);
        Assertions.assertFalse(contactRectangle.testContact(polygon));

    }
}
