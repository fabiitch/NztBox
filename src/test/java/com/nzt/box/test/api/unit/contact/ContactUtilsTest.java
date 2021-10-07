package com.nzt.box.test.api.unit.contact;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.ContactUtils;
import com.nzt.box.test.api.mock.FixtureMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactUtilsTest {

    Body bodyA, bodyB;
    FixtureMock fixtureA1, fixtureA2;
    FixtureMock fixtureB1, fixtureB2;

    @BeforeEach
    public void init() {
        bodyA = new Body(BodyType.Dynamic);
        fixtureA1 = new FixtureMock();
        fixtureA1.bodyShape.minDst = 5;
        fixtureA1.bodyShape.maxDst = 10;

        fixtureA2 = new FixtureMock();
        fixtureA2.bodyShape.minDst = 10;
        fixtureA2.bodyShape.maxDst = 20;

        bodyA.minDstFixture = 5;
        bodyA.maxDstFixture = 20;

        bodyB = new Body(BodyType.Dynamic);

        fixtureB1 = new FixtureMock();
        fixtureB1.bodyShape.minDst = 30;
        fixtureB1.bodyShape.maxDst = 50;

        fixtureB2 = new FixtureMock();
        fixtureB2.bodyShape.minDst = 50;
        fixtureB2.bodyShape.maxDst = 50;

        bodyB.minDstFixture = 30;
        bodyB.maxDstFixture = 50;

        bodyA.addFixture(fixtureA1);
        bodyA.addFixture(fixtureA2);
        bodyB.addFixture(fixtureB1);
        bodyB.addFixture(fixtureB2);
    }

    @Test
    public void canFixturesContact() {
        //A 5,10
        //B 30,50
        Assertions.assertEquals(1, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(35, 0);
        Assertions.assertEquals(1, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(36, 0);
        Assertions.assertEquals(0, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(60, 0);
        Assertions.assertEquals(0, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(61, 0);
        Assertions.assertEquals(-1, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyB.setPosition(0, 100);
        Assertions.assertEquals(-1, ContactUtils.fastCheck(fixtureA1, fixtureB1));
    }

    @Test
    public void canBodiesContact() {
        //A 10,20
        //B 50,50
        Assertions.assertEquals(1, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(60, 0);
        Assertions.assertEquals(1, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(61, 0);
        Assertions.assertEquals(0, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(70, 0);
        Assertions.assertEquals(0, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(71, 0);
        Assertions.assertEquals(-1, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(100, 0);
        Assertions.assertEquals(-1, ContactUtils.fastCheck(bodyA, bodyB));
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
