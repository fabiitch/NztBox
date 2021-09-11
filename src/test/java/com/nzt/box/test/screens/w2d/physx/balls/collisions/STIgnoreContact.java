package com.nzt.box.test.screens.w2d.physx.balls.collisions;

import com.badlogic.gdx.graphics.Color;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.screens.base.utils.BoxDebugUtils;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STIgnoreContact extends STFrontalBallCollision {

    private boolean done = false;

    public STIgnoreContact(FastTesterMain main) {
        super(main);
        world.contactListener = addContactListener();
    }

    private ContactListener addContactListener() {
        return new ContactListener() {
            @Override
            public void preSolve(ContactFixture contactFixture) {
                contactFixture.callNextMethods = true;
                HudDebug.addBotLeft("preSolve", true);
            }

            @Override
            public void beginContact(ContactFixture contactFixture) {
                HudDebug.addBotLeft("beginContact", true);
            }
            @Override
            public void endContact(ContactFixture contactFixture) {
                HudDebug.addBotLeft("endContact", true);
            }

            @Override
            public void continueContact(ContactFixture contactFixture) {
                HudDebug.addBotLeft("continueContact", true);
            }


        };
    }
}
