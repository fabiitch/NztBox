package com.nzt.box.test.s_try.base.debugger.breakpoints.factories.entities;

import com.nzt.box.bodies.Body;
import com.nzt.box.test.s_try.base.debugger.WorldDebugger;
import com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts.ContactWatcher;
import com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.entities.BodyWatcher;

public class BodyWatcherFactory {
    private BodyWatcher bodyWatcher;
    private WorldDebugger worldDebugger;

    public BodyWatcherFactory(WorldDebugger worldDebugger) {
        this.worldDebugger = worldDebugger;
    }

    public BodyWatcherFactory build() {
        if (bodyWatcher != null && bodyWatcher.entity != null)
            worldDebugger.breakPoints.add(bodyWatcher);
        return this;
    }

    public BodyWatcherFactory add(Body body) {
        bodyWatcher = new BodyWatcher(body);
        return this;
    }

    public BodyWatcherFactory add(int bodyId) {
        Body body = worldDebugger.data.getBody(bodyId);
        if (body != null)
            bodyWatcher = new BodyWatcher(body);
        return this;
    }

    public BodyWatcherFactory add(Object userData) {
        Body body = worldDebugger.data.getBody(userData);
        if (body != null)
            bodyWatcher = new BodyWatcher(body);
        return this;
    }

    public BodyWatcherFactory moveWatcher(boolean breakAtMove,
                                          boolean breakAtBodyCheckCollision,
                                          boolean breakAtFixtureCheckCollision) {
        bodyWatcher.breakAtMove = breakAtMove;
        bodyWatcher.breakAtBodyCheckCollision = breakAtBodyCheckCollision;
        bodyWatcher.breakAtFixtureCheckCollision = breakAtFixtureCheckCollision;
        return this;
    }

    public BodyWatcherFactory addContactWatcher(Body body) {
        bodyWatcher.bodiesContactWatchers.put(body, new ContactWatcher());
        return this;
    }

    public BodyWatcherFactory addContactWatcher(Body body, boolean breakAtBegin, boolean breakAtEnd, boolean breakAtContinue) {
        bodyWatcher.bodiesContactWatchers.put(body, new ContactWatcher(breakAtBegin, breakAtEnd, breakAtContinue));
        return this;
    }
}
