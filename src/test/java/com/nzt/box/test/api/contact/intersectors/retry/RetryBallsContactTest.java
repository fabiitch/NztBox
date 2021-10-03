package com.nzt.box.test.api.contact.intersectors.retry;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.api.BaseNztBoxTest;
import com.nzt.box.test.api.BoxTestMethods;
import com.nzt.box.test.api.mock.ContactListenerMock;
import com.nzt.gdx.test.api.tester.PredicateSuccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RetryBallsContactTest extends BaseNztBoxTest {

    protected Body ball1, ball2;
    private static final int NB_TEST = 50;

    private boolean collision = false;
    private ContactListener contactListener;

    CollisionData collisionData = null;
    int nbCollision = 0;

    public RetryBallsContactTest() {
        contactListener = new ContactListenerMock() {
            @Override
            public void beginContact(ContactFixture contactFixture) {
                collision = true;
                nbCollision++;
                if (collisionData == null) {
                    collisionData = contactFixture.collisionData;
                } else {
                    BoxTestMethods.equals(collisionData, contactFixture.collisionData);
                }
            }
        };
    }

    @Test
    public void retryContactTest() {
        for (int i = 0; i < NB_TEST; i++) {
            initNztBox();
            collision = false;
            world.contactListener = contactListener;
            ball1 = createBall(BodyType.Dynamic);
            ball2 = createBall(BodyType.Dynamic);
            world.addBody(ball1);
            world.addBody(ball2);

            ball1.setPosition(-200, 0);
            ball2.setPosition(0, -200);

            ball1.setVelocity(200, 0);
            ball2.setVelocity(0, 200);
            successesConditions.add(new PredicateSuccess() {
                @Override
                public String name() {
                    return "Collision !";
                }

                @Override
                public boolean testOk() {
                    return collision == true;
                }
            });

            renderLoopRdm();
        }
        Assertions.assertEquals(NB_TEST, nbCollision);
    }
}
