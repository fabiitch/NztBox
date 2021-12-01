package com.nzt.box.test.api.run;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.api.mock.BodyMock;
import com.nzt.box.test.api.mock.ContactListenerMock;
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

        bodyA = new BodyMock(BodyType.Kinematic,"A");
        fixtureA = bodyA.fixtures.get(0);

        bodyB = new BodyMock(BodyType.Kinematic,"B");
        fixtureB = bodyB.fixtures.get(0);

        bodyC = new BodyMock(BodyType.Kinematic,"C");
        fixtureC = bodyC.fixtures.get(0);

        world.addBody(bodyA);
        world.addBody(bodyB);
        world.addBody(bodyC);

        contactListener = new ContactListenerMock();
        world.setContactListener(contactListener);
    }
}
