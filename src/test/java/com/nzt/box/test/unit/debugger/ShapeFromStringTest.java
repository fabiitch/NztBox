package com.nzt.box.test.unit.debugger;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

 class ShapeFromStringTest {

    @Test
    public void rectangleTest() {
        Rectangle rectangle = ShapeFromString.rectangle("[50.0,55.545,20.0,10.0]");
        Assertions.assertEquals(50, rectangle.x);
        Assertions.assertEquals(55.545f, rectangle.y);
        Assertions.assertEquals(20, rectangle.width);
        Assertions.assertEquals(10, rectangle.height);
    }

    @Test
    public void circleTest() {
        Circle circle = ShapeFromString.circle("50.0,33.0,66.0");
        Assertions.assertEquals(50, circle.x);
        Assertions.assertEquals(33, circle.y);
        Assertions.assertEquals(66, circle.radius);
    }

    @Test
    public void polygon() {
        Polygon polygon = ShapeFromString.polygon("[-12.867004, -288.01547," +
                " 37.132996, -238.01547, " +
                "87.132996, -288.01547," +
                " -12.867004, -338.01547]");
        Assertions.assertEquals(8, polygon.getVertices().length);
        Assertions.assertEquals(-12.867004, polygon.getTransformedVertices()[0],0.1f);
        Assertions.assertEquals(-288.01547, polygon.getTransformedVertices()[1],0.1f);
        Assertions.assertEquals(37.132996, polygon.getTransformedVertices()[2],0.1f);
        Assertions.assertEquals(-238.01547, polygon.getTransformedVertices()[3],0.1f);
        Assertions.assertEquals(87.132996, polygon.getTransformedVertices()[4],0.1f);
        Assertions.assertEquals(-288.01547, polygon.getTransformedVertices()[5],0.1f);
        Assertions.assertEquals(-12.867004, polygon.getTransformedVertices()[6],0.1f);
        Assertions.assertEquals(-338.01547, polygon.getTransformedVertices()[7],0.1f);
    }
}
