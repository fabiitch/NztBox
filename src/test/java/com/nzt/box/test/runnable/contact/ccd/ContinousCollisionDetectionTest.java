//package com.nzt.box.test.run.contact.ccd;
//
//import com.nzt.box.bodies.Body;
//import com.nzt.box.contact.data.ContactFixture;
//import com.nzt.box.test.api.mock.ContactListenerMock;
//import com.nzt.box.test.run.BaseBoxTest;
//import com.nzt.gdx.test.runnable.tester.conditions.PredicateSuccess;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//@Disabled
////TODO
//public class ContinousCollisionDetectionTest extends BaseBoxTest {
//    boolean contactDone = false;
//
//    @Test
//    @Disabled
//    public void ccdTest() {
//        Body ball = boxSTHelp.createDynamicCircle(10, v(0, 0), v(5000000, 0));
//        Body wall = boxSTHelp.createRect(50, 500, boxSTHelp.basicStaticBodyDef.cpy().restitution(0), v(50, 0), v(0, 0), "Wall");
//        world.contactListener = new ContactListenerMock() {
//
//            @Override
//            public void preSolve(ContactFixture contactFixture) {
//                contactDone = true;
//            }
//        };
//        successesConditions.add(new PredicateSuccess() {
//            @Override
//            public String name() {
//                return "preSolve called";
//            }
//
//            @Override
//            public boolean testOk() {
//                return contactDone;
//            }
//        });
//        renderLoop60FPS();
//    }
//}
