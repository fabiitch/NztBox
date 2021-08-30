package com.nzt.box.test.api.data;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.api.mock.BodyMock;
import com.nzt.box.test.api.mock.FixtureMock;
import com.nzt.box.world.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorldDataTest {

    private World world;

    @BeforeEach
    public void init() {
        world = new World();
    }

    @Test
    public void removeBodyTest() {
        Body bodyA = new BodyMock();
        Fixture<?> fixtureA = new FixtureMock();
        bodyA.addFixture(fixtureA);

        Body bodyB = new BodyMock();
        Fixture<?> fixtureB = new FixtureMock();
        bodyB.addFixture(fixtureB);

        world.addBody(bodyA);
        world.addBody(bodyB);
        Assertions.assertEquals(2, world.data.bodies.size);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));

        world.step(0.1f);
        Assertions.assertNotNull(world.data.getContact(fixtureA, fixtureB));
        Assertions.assertEquals(1, fixtureA.contacts.size);
        Assertions.assertEquals(1, fixtureB.contacts.size);

        world.removeBody(bodyA);
        Assertions.assertEquals(1, world.data.bodies.size);
        Assertions.assertNull(world.data.getContact(fixtureA, fixtureB));
//        Assertions.assertEquals(0, fixtureA.contacts.size);
        Assertions.assertEquals(0, fixtureB.contacts.size);

    }
}
