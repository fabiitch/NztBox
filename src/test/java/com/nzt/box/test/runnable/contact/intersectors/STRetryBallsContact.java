package com.nzt.box.test.runnable.contact.intersectors;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.runnable.BaseRunnableBoxTest;
import com.nzt.box.test.unit.BoxTestMethods;
import com.nzt.box.test.mock.ContactListenerMock;
import com.nzt.gdx.test.st.tester.conditions.PredicateSuccess;
import com.nzt.gdx.mains.FastTesterMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Vérif que la collision arrive bien a chaque fois
 */
public class STRetryBallsContact extends BaseRunnableBoxTest {

    protected Body ball1, ball2;
    private static final int NB_TEST = 50;

    private boolean collision = false;
    private ContactListener contactListener;

    CollisionData collisionData = null;
    int nbCollision = 0;

    public STRetryBallsContact(FastTesterMain main) {
        super(main);
        this.maxTimeTestDuration = 5;
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
            this.timeElapsed = 0;
            initNztBox();
            collision = false;
            world.setContactListener(contactListener);
            ball1 = createBall(BodyType.Dynamic);
            ball2 = createBall(BodyType.Dynamic);

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
