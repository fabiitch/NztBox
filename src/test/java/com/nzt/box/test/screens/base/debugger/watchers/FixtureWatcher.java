package com.nzt.box.test.screens.base.debugger.watchers;

import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.screens.base.debugger.BreakPoint;

public class FixtureWatcher implements BreakPoint {

    public Fixture fixture;

    public boolean breakAtMove, breakAtBodyCheckCollision, breakAtFixtureCheckCollision;

    @Override
    public String type() {
        return null;
    }
}
