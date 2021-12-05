package com.nzt.box.test.s_try.base.debugger;

import com.nzt.box.test.s_try.base.debugger.breakpoints.factories.entities.BodyWatcherFactory;

public class WatcherFactories {

    public BodyWatcherFactory bodyFactory;

    public WatcherFactories(WorldDebugger worldDebugger) {
        bodyFactory = new BodyWatcherFactory(worldDebugger);
    }
}
