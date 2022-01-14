package com.nzt.box.test.s_try.w2d.collisions.twobody.events;

import com.badlogic.gdx.graphics.Color;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.collision.events")
public class STryContinusContactWithNoMvt extends BaseBox2DSTry {
    private int continusCall = 0;

    public STryContinusContactWithNoMvt(FastTesterMain main) {
        super(main);
        addContactListener();

    }


    @Override
    public void doRender(float dt) {

    }

    public void addContactListener() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(ContactFixture contactBody) {
//                HudDebug.update("Collision", true, Color.RED);
            }

            @Override
            public void endContact(ContactFixture contactBody) {
                HudDebug.update("Collision", false, Color.BLUE);
            }

            @Override
            public void continueContact(ContactFixture contactBody) {
                HudDebug.update("continusCall", ++continusCall);
            }

            @Override
            public void preSolve(ContactFixture contactBody) {

            }
        });
    }

    @Override
    public String getTestExplication() {
        return "Test if continus contact is called if body dont move ";
    }
}
