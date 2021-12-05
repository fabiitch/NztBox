package com.nzt.box.test.unit.contact;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.mock.BodyMock;
import com.nzt.box.test.mock.ContactListenerMock;
import com.nzt.box.world.World;
import org.junit.jupiter.api.BeforeEach;

public abstract class Base2BodyContactTest {
    protected World world;
    protected Body bodyA, bodyB;
    protected Fixture<?> fixtureA, fixtureB;
    protected ContactListener contactListener;

    @BeforeEach
    public void init() {
        world = new World();

        bodyA = new BodyMock("A");
        fixtureA = bodyA.fixtures.get(0);

        bodyB = new BodyMock("B");
        fixtureB = bodyB.fixtures.get(0);

        world.addBody(bodyA);
        world.addBody(bodyB);

        contactListener = new ContactListenerMock();
        world.setContactListener(contactListener);
    }
}
