package com.nzt.box.test.api.contact.utils;

import com.nzt.box.contact.ContactUtils;
import com.nzt.box.test.api.contact.BasicContactTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactUtilsTest extends BasicContactTest {

    @BeforeEach
    public void test() {

    }

    @Test
    public void canFixturesContact() {
        fixtureA.bodyShape.maxDst = 50;
        bodyA.maxDstFixture = 50;
        fixtureB.bodyShape.maxDst = 25;
        bodyB.maxDstFixture = 25;

        bodyA.setPosition(50, 50);
        Assertions.assertTrue(ContactUtils.canContact(fixtureA, fixtureB));

        bodyA.setPosition(100, 100);
        Assertions.assertFalse(ContactUtils.canContact(fixtureA, fixtureB));

        bodyB.setPosition(100, 100);
        Assertions.assertTrue(ContactUtils.canContact(fixtureA, fixtureB));
    }

    @Test
    public void canBodiesContact() {
        fixtureA.bodyShape.maxDst = 50;
        bodyA.maxDstFixture = 50;
        fixtureB.bodyShape.maxDst = 25;
        bodyB.maxDstFixture = 25;

        bodyA.setPosition(50, 50);
        Assertions.assertTrue(ContactUtils.canContact(bodyA, bodyB));

        bodyA.setPosition(100, 100);
        Assertions.assertFalse(ContactUtils.canContact(bodyA, bodyB));

        bodyB.setPosition(100, 100);
        Assertions.assertTrue(ContactUtils.canContact(bodyA, bodyB));
    }

    @Test
    public void canBodyAndFixtureContact() {
        fixtureA.bodyShape.maxDst = 50;
        bodyA.maxDstFixture = 50;
        fixtureB.bodyShape.maxDst = 25;
        bodyB.maxDstFixture = 25;

        bodyA.setPosition(50, 50);
        Assertions.assertTrue(ContactUtils.canContact(bodyA, fixtureB));

        bodyA.setPosition(100, 100);
        Assertions.assertFalse(ContactUtils.canContact(bodyA, fixtureB));

        bodyB.setPosition(100, 100);
        Assertions.assertTrue(ContactUtils.canContact(bodyA, fixtureB));
    }
}
