package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;

public class FixtureContactWatcher extends EntityContactWatcher<Fixture> {

    public FixtureContactWatcher(Fixture entityA, Fixture entityB) {
        super(entityA, entityB);
    }

    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return false;
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return false;
    }

    @Override
    public boolean breakForContact(Fixture fixtureA, Fixture fixtureB, BreakPoints action) {
        return hasFixtures(fixtureA, fixtureB)
                && contactWatcher.breakForContact(fixtureA, fixtureB, action);
    }

    boolean hasFixtures(Fixture fixtureA, Fixture fixtureB) {
        return (this.entityA == fixtureA || this.entityA == fixtureB)
                && (this.entityB == fixtureA || this.entityB == fixtureB);
    }
}
