package com.nzt.box.test.unit.contact;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactUtils;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
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

        bodyA.setPosition(29.99999f, 0);//stick before
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(30, 0);//stick
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(30.0001f, 0);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(31, 0);
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 29.9999f);//stick before
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 30);//stick
        Assertions.assertEquals(true, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 30.001f);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));

        bodyA.setPosition(0, 31);
        Assertions.assertEquals(false, ContactUtils.fastCheck(fixtureA1, fixtureB1));
    }

    @Test
    public void canBodiesContact() {
        //A 30,20
        //B 50,40
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));
        bodyA.setPosition(15, 0); //boxA x=0
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        float bodyBDecalX = 25;
        bodyB.setPosition(29.99999f + bodyBDecalX, 0);//stick before
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(30 + bodyBDecalX, 0);//stick
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(30.00001f + bodyBDecalX, 0);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(55 + bodyBDecalX, 0);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));

        bodyA.setPosition(0, 10); //boxA y=0
        float bodyBDecalY = 20;
        bodyB.setPosition(0, 15 + bodyBDecalY);
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(0, 19.99999f + bodyBDecalY);//stick before
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(0, 20 + bodyBDecalY);//stick
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(0, 20.0001f + bodyBDecalY);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));

        bodyB.setPosition(0, 33 + bodyBDecalY);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, bodyB));
    }

    @Test
    public void canBodyAndFixtureContact() {
        //bodyA 30,20
        //fixtureB 40,40
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        //X  A=30 | B=40
        bodyB.setPosition(20, 0); //boxB x=0
        float bodyADecalX = 15;

        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));
        bodyA.setPosition(30 + bodyADecalX, 0);//large in
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(39.9999f + bodyADecalX, 0);//stick before
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(40 + bodyADecalX, 0);//stick
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(40.00001f + bodyADecalX, 0);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(55 + bodyADecalX, 0);//large out
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, fixtureB1));

        // y A =20, B= 40

        bodyB.setPosition(0, 20); //boxB y=0
        float bodyADecalY = 10f;

        bodyA.setPosition(0,10 + bodyADecalY);//large in
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(0,39.9999f + bodyADecalY);//stick before
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(0,40 + bodyADecalY);//stick
        Assertions.assertEquals(true, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(0,40.00001f + bodyADecalY);//stick after
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, fixtureB1));

        bodyA.setPosition(0,45 + bodyADecalY);//large out
        Assertions.assertEquals(false, ContactUtils.fastCheck(bodyA, fixtureB1));
    }
}
