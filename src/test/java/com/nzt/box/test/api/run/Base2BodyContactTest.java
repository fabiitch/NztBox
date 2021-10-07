package com.nzt.box.test.api.run;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.api.mock.BodyMock;
import com.nzt.box.test.api.mock.ContactListenerMock;
import com.nzt.box.test.api.mock.FixtureMock;
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
        fixtureA = new FixtureMock("A");
        bodyA.addFixture(fixtureA);

        bodyB = new BodyMock("B");
        fixtureB = new FixtureMock("B");
        bodyB.addFixture(fixtureB);

        world.addBody(bodyA);
        world.addBody(bodyB);

        contactListener = new ContactListenerMock();
        world.contactListener = contactListener;
    }
}
