package com.nzt.box.test.api.data;

import com.nzt.box.test.api.contact.Base2BodyContactTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldDataTest extends Base2BodyContactTest {

    @Test
    public void removeBodyTest() {
        Assertions.assertEquals(2, world.data.bodies.size);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));

        world.step(0.1f);
        Assertions.assertNotNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertEquals(1, fixtureA.contacts.size);
        Assertions.assertEquals(1, fixtureB.contacts.size);

        world.destroyBody(bodyA);
        Assertions.assertEquals(1, world.data.bodies.size);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureB.contacts.size);
    }

    @Test
    public void endContact() {
        Assertions.assertEquals(2, world.data.bodies.size);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureA.contacts.size);

        world.step(0.1f); //detectContact

        Assertions.assertNotNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertEquals(1, fixtureA.contacts.size);
        Assertions.assertEquals(1, fixtureB.contacts.size);

        bodyA.setPosition(100, 100);
        world.step(0.1f); //endContact
        Assertions.assertEquals(2, world.data.bodies.size);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureB.contacts.size);

    }
}
