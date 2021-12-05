package com.nzt.box.test.s_try.base.debugger.breakpoints;

import com.badlogic.gdx.Gdx;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;

public interface BreakPoint {

    boolean breakForBody(Body body, BreakPoints action);

    boolean breakForFixture(Fixture fixture, BreakPoints action);

    boolean breakForContact(Fixture fixtureA, Fixture fixtureB, BreakPoints action);

    default void callBreakCode(BreakPoints action) {
        //put brekpoint here
        Gdx.app.log("BreakPoint" + this.getClass().getSimpleName(), action.name());
    }

}
