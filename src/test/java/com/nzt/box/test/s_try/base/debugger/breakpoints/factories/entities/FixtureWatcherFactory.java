package com.nzt.box.test.s_try.base.debugger.breakpoints.factories.entities;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.WorldDebugger;
import com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.entities.FixtureWatcher;

public class FixtureWatcherFactory {

    private FixtureWatcher fixtureWatcher;
    private WorldDebugger worldDebugger;

    public FixtureWatcherFactory(WorldDebugger worldDebugger) {
        this.worldDebugger = worldDebugger;
    }

    public FixtureWatcher build() {
        if (fixtureWatcher != null && fixtureWatcher.entity != null)
            return fixtureWatcher;
        return null;
    }

    public FixtureWatcherFactory get(Fixture fixture) {
        fixtureWatcher = new FixtureWatcher(fixture);
        return this;
    }

    public FixtureWatcherFactory get(Body body, int indexFixture) {
        fixtureWatcher = new FixtureWatcher(body.fixtures.get(indexFixture));
        return this;
    }

    public FixtureWatcherFactory contactWatcher(boolean breakAtMove, boolean breakAtBodyCheckCollision, boolean breakAtFixtureCheckCollision) {
        fixtureWatcher.breakAtMove = breakAtMove;
        fixtureWatcher.breakAtBodyCheckCollision = breakAtBodyCheckCollision;
        fixtureWatcher.breakAtFixtureCheckCollision = breakAtFixtureCheckCollision;
        return this;
    }
}
