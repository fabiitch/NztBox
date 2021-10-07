package com.nzt.box.test.api.run.contact.data;

import com.nzt.box.test.api.run.Base3BodyContactTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactDataTest extends Base3BodyContactTest {

    @Test
    public void testFixturesContacts() {
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureB.contacts.size);
        Assertions.assertEquals(0, fixtureC.contacts.size);

        world.step(0.1f);
        Assertions.assertEquals(2, fixtureA.contacts.size);
        Assertions.assertEquals(2, fixtureB.contacts.size);
        Assertions.assertEquals(2, fixtureC.contacts.size);

        world.destroyBody(bodyA);
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(1, fixtureB.contacts.size);
        Assertions.assertEquals(1, fixtureC.contacts.size);

        world.destroyBody(bodyB);
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureB.contacts.size);
        Assertions.assertEquals(0, fixtureC.contacts.size);
    }

    @Test
    public void testBodiesContacts() {
        Assertions.assertEquals(0, bodyA.contacts.size);
        Assertions.assertEquals(0, bodyB.contacts.size);
        Assertions.assertEquals(0, bodyC.contacts.size);

        world.step(0.1f);
        Assertions.assertEquals(2, bodyA.contacts.size);
        Assertions.assertEquals(2, bodyB.contacts.size);
        Assertions.assertEquals(2, bodyC.contacts.size);

        world.destroyBody(bodyA);
        Assertions.assertEquals(0, bodyA.contacts.size);
        Assertions.assertEquals(1, bodyB.contacts.size);
        Assertions.assertEquals(1, bodyC.contacts.size);

        world.destroyBody(bodyB);
        Assertions.assertEquals(0, bodyA.contacts.size);
        Assertions.assertEquals(0, bodyB.contacts.size);
        Assertions.assertEquals(0, bodyC.contacts.size);
    }

    @Test
    public void testMapContactFixtures() {
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureB, fixtureA));
        Assertions.assertNull(world.data.getContact(fixtureB, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureC, fixtureA));
        Assertions.assertNull(world.data.getContact(fixtureC, fixtureB));

        world.step(0.1f);
        Assertions.assertNotNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertNotNull(world.data.getContact(fixtureA, fixtureC));

        Assertions.assertNotNull(world.data.getContact(fixtureB, fixtureA));
        Assertions.assertNotNull(world.data.getContact(fixtureB, fixtureC));

        Assertions.assertNotNull(world.data.getContact(fixtureC, fixtureA));
        Assertions.assertNotNull(world.data.getContact(fixtureC, fixtureB));

        world.destroyBody(bodyA);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureB, fixtureA));
        Assertions.assertNotNull(world.data.getContact(fixtureB, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureC, fixtureA));
        Assertions.assertNotNull(world.data.getContact(fixtureC, fixtureB));

        world.destroyBody(bodyB);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureB, fixtureA));
        Assertions.assertNull(world.data.getContact(fixtureB, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureC, fixtureA));
        Assertions.assertNull(world.data.getContact(fixtureC, fixtureB));
    }

    @Test
    public void testMapContactBodies() {
        Assertions.assertNull(world.data.getContact(bodyA, bodyB));
        Assertions.assertNull(world.data.getContact(bodyA, bodyC));

        Assertions.assertNull(world.data.getContact(bodyB, bodyA));
        Assertions.assertNull(world.data.getContact(bodyB, bodyC));

        Assertions.assertNull(world.data.getContact(bodyC, bodyA));
        Assertions.assertNull(world.data.getContact(bodyC, bodyB));

        world.step(0.1f);
        Assertions.assertNotNull(world.data.getContact(bodyA, bodyB));
        Assertions.assertNotNull(world.data.getContact(bodyA, bodyC));

        Assertions.assertNotNull(world.data.getContact(bodyB, bodyA));
        Assertions.assertNotNull(world.data.getContact(bodyB, bodyC));

        Assertions.assertNotNull(world.data.getContact(bodyC, bodyA));
        Assertions.assertNotNull(world.data.getContact(bodyC, bodyB));

        world.destroyBody(bodyA);
        Assertions.assertNull(world.data.getContact(bodyA, bodyB));
        Assertions.assertNull(world.data.getContact(bodyA, bodyC));

        Assertions.assertNull(world.data.getContact(bodyB, bodyA));
        Assertions.assertNotNull(world.data.getContact(bodyB, bodyC));

        Assertions.assertNull(world.data.getContact(bodyC, bodyA));
        Assertions.assertNotNull(world.data.getContact(bodyC, bodyB));

        world.destroyBody(bodyB);
        Assertions.assertNull(world.data.getContact(bodyA, bodyB));
        Assertions.assertNull(world.data.getContact(bodyA, bodyC));

        Assertions.assertNull(world.data.getContact(bodyB, bodyA));
        Assertions.assertNull(world.data.getContact(bodyB, bodyC));

        Assertions.assertNull(world.data.getContact(bodyC, bodyA));
        Assertions.assertNull(world.data.getContact(bodyC, bodyB));
    }
}
