package com.nzt.box.test.screens.base.debugger.watchers;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.screens.base.debugger.BreakPoint;
import com.nzt.box.test.screens.base.debugger.BreakPoints;

public class BodyWatcher implements BreakPoint {

    public Body body;

    public boolean breakAtMove, breakAtBodyCheckCollision, breakAtFixtureCheckCollision;

    public ContactWatcher contactWatcher;


    public BodyWatcher(Body body) {
        this.body = body;
    }

    public boolean stop(Body body) {
        return body == this.body;
    }


    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return this.body == body;
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return this.body.fixtures.contains(fixture, true);
    }

    @Override
    public String type() {
        return "BodyWatcher";
    }


}
