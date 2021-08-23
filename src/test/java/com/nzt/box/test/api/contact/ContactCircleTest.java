package com.nzt.box.test.api.contact;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.detector.impl.ContactCircle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactCircleTest implements ContactTest {

    private static ContactCircle contactCircle = new ContactCircle();

    @BeforeEach
    @Override
    public void init() {
        Circle c1 = new Circle(0, 0, 150);
        contactCircle.myCircle = c1;
    }

    @Test
    @Override
    public void contactCircle() {
        Circle c2 = new Circle(0, 0, 50);
        Assertions.assertTrue(contactCircle.testContact(c2));

        c2.setPosition(200, 0);
        Assertions.assertTrue(contactCircle.testContact(c2));

        c2.setPosition(200.01f, 0);
        Assertions.assertFalse(contactCircle.testContact(c2));

        c2.setRadius(51);
        Assertions.assertTrue(contactCircle.testContact(c2));

        c2.setPosition(150f, 150);

        Assertions.assertFalse(contactCircle.testContact(c2));
    }

    @Test
    @Override
    public void contactRectangle() {
        Rectangle rect = new Rectangle(0, 0, 100, 50);

        Assertions.assertTrue(contactCircle.testContact(rect));

        rect.setPosition(151, 0);
        Assertions.assertFalse(contactCircle.testContact(rect));
    }


    @Test
    @Override
    public void contactPolygon() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0};
        Polygon polygon = new Polygon(vertices);

        Assertions.assertTrue(contactCircle.testContact(polygon));

        polygon.setPosition(151, 0);
        Assertions.assertFalse(contactCircle.testContact(polygon));
    }
}
