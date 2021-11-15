package com.nzt.box.test.api.unit.contact;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.api.math.AbstractMathTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test merge de boudingbox
 */
public class BodyBoundingBoxTest {

    @Test
    public void bodyBoudingBoxTest() {
        Body body = new Body(BodyType.Static);
        Assertions.assertEquals(AbstractMathTest.r(), body.boundingBox);

        Fixture fixtureA = new Fixture(new CircleShape(10));
        body.addFixture(fixtureA);
        Assertions.assertEquals(AbstractMathTest.r(-10, -10, 20, 20), body.boundingBox);

        body.setPosition(10, 10);
        Assertions.assertEquals(AbstractMathTest.r(0, 0, 20, 20), body.boundingBox);

        Fixture fixtureB = new Fixture(new CircleShape(20));
        body.addFixture(fixtureB);
        Assertions.assertEquals(AbstractMathTest.r(-10, -10, 40, 40), body.boundingBox);

        body.setPosition(20, 20);
        Assertions.assertEquals(AbstractMathTest.r(0, 0, 40, 40), body.boundingBox);

        Fixture fixtureC = new Fixture(new RectangleShape(100, 10));
        body.addFixture(fixtureC);
        body.setPosition(0, 0);
        Assertions.assertEquals(AbstractMathTest.r(-50, -20, 100, 40), body.boundingBox);

        body.setPosition(100, 100);
        Assertions.assertEquals(AbstractMathTest.r(0, 0, 100, 40), body.boundingBox);
    }
}
