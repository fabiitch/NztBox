package com.nzt.box.test.api.run;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.api.mock.BodyMock;
import com.nzt.box.test.api.mock.ContactListenerMock;
import com.nzt.box.test.api.mock.FixtureMock;
import com.nzt.box.world.World;
import org.junit.jupiter.api.BeforeEach;

public abstract class Base3BodyContactTest {

    protected World world;
    protected Body bodyA, bodyB, bodyC;
    protected Fixture<?> fixtureA, fixtureB, fixtureC;
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

        bodyC = new BodyMock("C");
        fixtureC = new FixtureMock("C");
        bodyC.addFixture(fixtureC);

        world.addBody(bodyA);
        world.addBody(bodyB);
        world.addBody(bodyC);

        contactListener = new ContactListenerMock();
        world.contactListener = contactListener;
    }
}
