package com.nzt.box.test.api.unit.contact;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.api.mock.FixtureMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactUtilsTest {

    Body bodyA, bodyB;
    Fixture fixtureA1, fixtureA2;
    Fixture fixtureB1, fixtureB2;

    @BeforeEach
    public void init() {
        bodyA = new Body(BodyType.Dynamic);
        fixtureA1 = new Fixture(new CircleShape(10));
        fixtureA2 = new Fixture(new RectangleShape(30, 5));
        bodyA.addFixture(fixtureA1);
        bodyA.addFixture(fixtureA2);

        bodyB = new Body(BodyType.Dynamic);
        fixtureB1 = new Fixture(new CircleShape(20));
        fixtureB2 = new Fixture(new RectangleShape(50, 7));
        bodyB.addFixture(fixtureB1);
        bodyB.addFixture(fixtureB2);
    }

    @Test
    public void canFixturesContact() {
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(30, 0);
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(30.0001f, 0);
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(31, 0);
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 30);
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 30.001f);
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 31);
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));
    }

    @Test
    public void canBodiesContact() {
        //A 10,20
        //B 50,50
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(40, 0);
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(40.001f, 0);
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(41, 0);
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(0, 12);
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(0, 12.0001f);
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(0, 13f);
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));
    }

    @Test
    public void canBodyAndFixtureContact() {
        //A 10,20
        //B 30,50
        Assertions.assertEquals(1, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(40, 0);
        Assertions.assertEquals(1, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(41, 0);
        Assertions.assertEquals(0, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(70, 0);
        Assertions.assertEquals(0, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(71, 0);
        Assertions.assertEquals(-1, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(100, 0);
        Assertions.assertEquals(-1, ContactUtils.fastCheck(bodyA, fixtureB1));

    }
}
