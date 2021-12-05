package com.nzt.box.test.s_try.base.debugger;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoint;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;
import com.nzt.box.world.World;

public class WorldDebugger extends World {

    public Array<BreakPoint> breakPoints = new Array<>();
    public ContactListenerDebugger contactListener;
    public WatcherFactories watcherFactories;

    public WorldDebugger(float stepTime) {
        super(stepTime, true);
        contactListener = new ContactListenerDebugger(this);
        watcherFactories = new WatcherFactories(this);
    }

    public WorldDebugger() {
        this(1f / 60f / 8f);
    }

    @Override
    public void setContactListener(ContactListener contactListener) {
        this.contactListener.contactListener = contactListener;
    }

    @Override
    protected void moveBody(Body body) {
        boolean shouldBreak = breakForBody(body, BreakPoints.BodyMove);
        super.moveBody(body);
    }

    @Override
    protected void checkBodyCollisions(Body bodyA) {
        boolean shouldBreak = breakForBody(bodyA, BreakPoints.BodyCheckCollision);
        super.checkBodyCollisions(bodyA);
    }

    @Override
    protected void checkFixtureCollision(Body bodyA, Fixture fixtureA) {
        boolean shouldBreak = breakForFixture(fixtureA, BreakPoints.FixtureCheckCollision);
        super.checkFixtureCollision(bodyA, fixtureA);
    }

    @Override
    protected void fixtureTestCollision(Fixture fixtureA, Fixture fixtureB) {
        super.fixtureTestCollision(fixtureA, fixtureB);
    }

    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        BreakPoint breakPointCall = null;
        for (BreakPoint breakPoint : breakPoints) {
            if (breakPoint.breakForFixture(fixture, action)) {
                breakPointCall = breakPoint;
                break;
            }
        }
        if (breakPointCall != null) {
            breakPointCall.callBreakCode(action);
        }
        return breakPointCall != null;
    }

    public boolean breakForBody(Body body, BreakPoints action) {
        BreakPoint breakPointCall = null;
        for (BreakPoint breakPoint : breakPoints) {
            if (breakPoint.breakForBody(body, action)) {
                breakPointCall = breakPoint;
                break;
            }
        }
        if (breakPointCall != null) {
            breakPointCall.callBreakCode(action);
        }
        return breakPointCall != null;
    }

}
