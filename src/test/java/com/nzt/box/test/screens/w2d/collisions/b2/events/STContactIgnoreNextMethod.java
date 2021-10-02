//package com.nzt.box.test.screens.w2d.collisions.events;
//
//import com.nzt.box.contact.data.ContactFixture;
//import com.nzt.box.contact.listener.ContactListener;
//import com.nzt.gdx.debug.hud.core.HudDebug;
//import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
//
//public class STContactIgnoreNextMethod extends STFrontalBallCollision {
//
//    private boolean done = false;
//
//    public STContactIgnoreNextMethod(FastTesterMain main) {
//        super(main);
//        world.contactListener = addContactListener();
//    }
//
//    private ContactListener addContactListener() {
//        return new ContactListener() {
//            @Override
//            public void preSolve(ContactFixture contactFixture) {
//                contactFixture.callNextMethods = false;
//                HudDebug.addBotLeft("preSolve", true);
//            }
//
//            @Override
//            public void beginContact(ContactFixture contactFixture) {
//                HudDebug.addBotLeft("beginContact", true);
//            }
//            @Override
//            public void endContact(ContactFixture contactFixture) {
//                HudDebug.addBotLeft("endContact", true);
//            }
//
//            @Override
//            public void continueContact(ContactFixture contactFixture) {
//                HudDebug.addBotLeft("continueContact", true);
//            }
//
//
//        };
//    }
//}
