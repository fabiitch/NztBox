package com.nzt.box.test.screens.base.debugger.watchers;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.screens.base.debugger.BreakPoint;
import com.nzt.box.test.screens.base.debugger.BreakPoints;

public class FixtureWatcher implements BreakPoint {

    public Fixture fixture;
    public ContactWatcher contactWatcher;
    public boolean breakAtMove, breakAtBodyCheckCollision, breakAtFixtureCheckCollision;

    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return this.fixture.body == body;
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return this.fixture == fixture;
    }

    @Override
    public String type() {
        return null;
    }
}
