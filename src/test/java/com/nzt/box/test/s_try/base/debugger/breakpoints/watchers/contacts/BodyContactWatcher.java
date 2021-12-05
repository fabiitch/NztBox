package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;

public class BodyContactWatcher extends EntityContactWatcher<Body> {

    public BodyContactWatcher(Body entityA, Body entityB) {
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
        return hasBodies(fixtureA.body, fixtureB.body)
                && contactWatcher.breakForContact(fixtureA, fixtureB, action);
    }

    boolean hasBodies(Body bodyA, Body bodyB) {
        return (this.entityA == bodyA || this.entityA == bodyB)
                && (this.entityB == bodyA || this.entityB == bodyB);
    }

}
