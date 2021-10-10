package com.nzt.box.test.api.run.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.test.api.run.BaseBoxTest;
import com.nzt.box.test.api.mock.ContactListenerMock;
import com.nzt.gdx.test.api.tester.PredicateSuccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * Use startComputeContactTest() for start simulation
 */
public abstract class BaseComputeContactTest extends BaseBoxTest {

    protected Body body1, body2;
    protected BodyDef bodyDef1, bodyDef2;
    protected Vector2 velocity1, velocity2;//vel start
    protected Vector2 position1, position2;//pos start

    protected ContactListenerMock contactListener;
    protected ContactComputeTester forcesTester;

    boolean contactDone = false;

    protected ForceDataResult data1 = new ForceDataResult(), data2 = new ForceDataResult();

    @BeforeEach
    public void beforeForceCalcul() {
        this.maxTimeTestDuration = 200;
        bodyDef1 = new BodyDef(BodyType.Dynamic);
        bodyDef2 = new BodyDef(BodyType.Dynamic);
        contactListener = new ContactListenerMock() {

            @Override
            public void preSolve(ContactFixture contactFixture) {
                contactDone = true;
            }
        };
        world.contactListener = contactListener;


        successesConditions.add(new PredicateSuccess() {
            @Override
            public String name() {
                return "preSolve called";
            }

            @Override
            public boolean testOk() {
                return contactDone;
            }
        });

    }

    private void checkPrecondition() {
        if (position1 == null) {
            Assertions.fail("position1 not defined");
        }
        if (position2 == null) {
            Assertions.fail("position2 not defined");
        }

        if (velocity1 == null) {
            Assertions.fail("velocity1 not defined");
        }
        if (velocity2 == null) {
            Assertions.fail("velocity2 not defined");
        }
    }

    /**
     * use this for start simulation
     */
    protected void startComputeContactTest() {
        checkPrecondition();
        body1 = createBall(bodyDef1);
        body2 = createBall(bodyDef2);

        data1.body = body1;
        data2.body = body2;

        body1.setPosition(position1);
        body2.setPosition(position2);

        body1.setVelocity(velocity1);
        body2.setVelocity(velocity2);

        forcesTester = new ContactComputeTester(data1, data2);
        world.contactCompute = forcesTester;
        successesConditions.add(new PredicateSuccess() {
            @Override
            public String name() {
                return "Compute contact called";
            }

            @Override
            public boolean testOk() {
                return forcesTester.computeDone;
            }
        });

        renderLoop60FPS();
    }


}
