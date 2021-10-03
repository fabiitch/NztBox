package com.nzt.box.test.api.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.test.api.BaseNztBoxTest;
import com.nzt.box.test.api.mock.ContactListenerMock;
import com.nzt.gdx.test.api.tester.PredicateSuccess;
import org.junit.jupiter.api.BeforeEach;

/**
 * use startTest in your method
 */
public abstract class BaseCalculForceTest extends BaseNztBoxTest {

    protected Body body1, body2;
    protected BodyDef bodyDef1, bodyDef2;
    protected Vector2 velocity1, velocity2;

    protected ContactListenerMock contactListener;
    protected ContactForcesTester forcesTester;

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

    protected void testForceCompute() {
        body1 = createBall(bodyDef1);
        body2 = createBall(bodyDef2);

        data1.body = body1;
        data2.body = body2;

        body1.setPosition(pos1());
        body2.setPosition(pos2());

        body1.setVelocity(velocity1);
        body2.setVelocity(velocity2);

        forcesTester = new ContactForcesTester(data1, data2);
        world.contactForces = forcesTester;
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

    protected abstract Vector2 pos1();

    protected abstract Vector2 pos2();

    protected Vector2 v(float x, float y) {
        return new Vector2(x, y);
    }

}
