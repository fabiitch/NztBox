package com.nzt.box.test.api.contact.bodytype;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.test.api.BaseNztBoxTest;
import com.nzt.box.test.api.mock.ContactListenerMock;
import com.nzt.gdx.test.api.fake.TestCondition;
import com.nzt.gdx.test.api.math.vectors.VTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Test Collision of BodyType with other //TODO
 */
@Disabled
public abstract class BaseBodyTypeContactTest extends BaseNztBoxTest {
    Body myBody;
    Body bodyTarget;

    public abstract BodyType bodyTypeToTest();

    public void createBodies() {
        myBody = createBall(bodyTypeToTest());
        bodyTarget = createBall(BodyType.Static);
        bodyTarget.setPosition(200, 0);
    }

    public abstract boolean canMove();

    public abstract boolean canRotate();//TODO


    public void goCollide() {
        myBody.setVelocity(50, 0);
        bodyTarget.setVelocity(-50, 0);
        renderLoopRdm();
    }

    public abstract BodyTypeTestResult withStatic();

    @Test
    public void testWithStatic() {
//        bodyTarget.bodyType = BodyType.Static;
//        addTestCondition(withStatic());
//        goCollide();
    }

//    public abstract BodyTypeTestResult withDynamic();
//
//    @Test
//    public void testWithDynamic() {
//        bodyTarget.bodyType = BodyType.Dynamic;
//        addTestCondition(withDynamic());
//        goCollide();
//    }
//
//    public abstract BodyTypeTestResult withKinematic();
//
//    @Test
//    public void testWithKinematic() {
//        bodyTarget.bodyType = BodyType.Kinematic;
//        addTestCondition(withKinematic());
//        goCollide();
//    }
//
//    public abstract BodyTypeTestResult withGhost();
//
//    @Test
//    public void testWithGhost() {
//        bodyTarget.bodyType = BodyType.Ghost;
//        addTestCondition(withGhost());
//        goCollide();
//    }
//
//    public abstract BodyTypeTestResult withForce();
//
//    @Test
//    public void testWithForce() {
//        bodyTarget.bodyType = BodyType.Force;
//        addTestCondition(withForce());
//        goCollide();
//    }

    protected void addTestCondition(BodyTypeTestResult bodyTypeTestResult) {
        createBodies();
        world.contactListener = new ContactListenerMock() {
            @Override
            public void beginContact(ContactFixture contactFixture) {
                Assertions.assertEquals(bodyTypeTestResult.shouldApplyForces, contactFixture.doCollision);
//                Assertions.assertEquals(bodyTypeTestResult.shouldRebound, contactFixture.doRebound);
            }
        };
        testConditions.add(new TestCondition() {
            @Override
            public String name() {
                return "Contact !";
            }

            @Override
            public Boolean ok() {
                if (bodyTypeTestResult.shouldGetEvent) {
                    if (myBody.fixtures.get(0).contacts.size == 1)
                        return true;
                } else {
                    if (myBody.fixtures.get(0).contacts.size == 0)
                        return false;
                }
                return null;
            }
        });
    }

    @Test
    public void testMove() {
        createBodies();
        Vector2 posStart = myBody.getPosition(new Vector2());
        myBody.setVelocity(50, 50);
        world.step(1);
        world.step(1);
        world.step(1);

        Vector2 posMove = myBody.getPosition(new Vector2());
        if (canMove()) {
            VTestUtils.assertNotEquals(posStart, posMove);
        } else {
            VTestUtils.assertEquals(posStart, posMove);
        }
    }


}
