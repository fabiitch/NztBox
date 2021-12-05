package com.nzt.box.test.unit.contact;

import com.nzt.box.test.unit.contact.Base3BodyContactTest;
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

        world.removeBody(bodyA);
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(1, fixtureB.contacts.size);
        Assertions.assertEquals(1, fixtureC.contacts.size);

        world.removeBody(bodyB);
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureB.contacts.size);
        Assertions.assertEquals(0, fixtureC.contacts.size);
    }

    @Test
    public void testBodiesContacts() {
        Assertions.assertEquals(0, bodyA.contactsBody.size);
        Assertions.assertEquals(0, bodyB.contactsBody.size);
        Assertions.assertEquals(0, bodyC.contactsBody.size);

        world.step(0.1f);
        Assertions.assertEquals(2, bodyA.contactsBody.size);
        Assertions.assertEquals(2, bodyB.contactsBody.size);
        Assertions.assertEquals(2, bodyC.contactsBody.size);

        world.removeBody(bodyA);
        Assertions.assertEquals(0, bodyA.contactsBody.size);
        Assertions.assertEquals(1, bodyB.contactsBody.size);
        Assertions.assertEquals(1, bodyC.contactsBody.size);

        world.removeBody(bodyB);
        Assertions.assertEquals(0, bodyA.contactsBody.size);
        Assertions.assertEquals(0, bodyB.contactsBody.size);
        Assertions.assertEquals(0, bodyC.contactsBody.size);
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

        world.removeBody(bodyA);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureB, fixtureA));
        Assertions.assertNotNull(world.data.getContact(fixtureB, fixtureC));

        Assertions.assertNull(world.data.getContact(fixtureC, fixtureA));
        Assertions.assertNotNull(world.data.getContact(fixtureC, fixtureB));

        world.removeBody(bodyB);
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

        world.removeBody(bodyA);
        Assertions.assertNull(world.data.getContact(bodyA, bodyB));
        Assertions.assertNull(world.data.getContact(bodyA, bodyC));

        Assertions.assertNull(world.data.getContact(bodyB, bodyA));
        Assertions.assertNotNull(world.data.getContact(bodyB, bodyC));

        Assertions.assertNull(world.data.getContact(bodyC, bodyA));
        Assertions.assertNotNull(world.data.getContact(bodyC, bodyB));

        world.removeBody(bodyB);
        Assertions.assertNull(world.data.getContact(bodyA, bodyB));
        Assertions.assertNull(world.data.getContact(bodyA, bodyC));

        Assertions.assertNull(world.data.getContact(bodyB, bodyA));
        Assertions.assertNull(world.data.getContact(bodyB, bodyC));

        Assertions.assertNull(world.data.getContact(bodyC, bodyA));
        Assertions.assertNull(world.data.getContact(bodyC, bodyB));
    }
}
