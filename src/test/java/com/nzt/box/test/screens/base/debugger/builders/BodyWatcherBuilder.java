package com.nzt.box.test.screens.base.debugger.builders;

import com.nzt.box.bodies.Body;
import com.nzt.box.test.screens.base.debugger.WorldDebugger;
import com.nzt.box.test.screens.base.debugger.watchers.BodyWatcher;
import com.nzt.box.test.screens.base.debugger.watchers.ContactWatcher;
import com.nzt.box.world.World;

public class BodyWatcherBuilder {

    private World world;
    private BodyWatcher bodyWatcher;

    public BodyWatcherBuilder(WorldDebugger world) {
        this.world = world;
    }

    public BodyWatcherBuilder get() {
        bodyWatcher = new BodyWatcher(null);
        return this;
    }

    public BodyWatcher build() {
        return bodyWatcher;
    }

    public BodyWatcherBuilder andBody(Body body) {
        bodyWatcher.body = body;
        return this;
    }

    public BodyWatcherBuilder breakAt(boolean move, boolean bodyCheckCollision, boolean fixtureCheckCollision) {
        bodyWatcher.breakAtMove = move;
        bodyWatcher.breakAtBodyCheckCollision = bodyCheckCollision;
        bodyWatcher.breakAtFixtureCheckCollision = fixtureCheckCollision;
        return this;
    }

    public BodyWatcherBuilder contactWatcher(boolean breakAtBegin, boolean breakAtEnd, boolean breakAtContinue) {
        ContactWatcher contactWatcher = bodyWatcher.contactWatcher;
        contactWatcher.breakAtBegin = breakAtBegin;
        contactWatcher.breakAtEnd = breakAtEnd;
        contactWatcher.breakAtContinue = breakAtContinue;
        return this;
    }
}
