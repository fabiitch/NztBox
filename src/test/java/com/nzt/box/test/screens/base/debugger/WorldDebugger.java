package com.nzt.box.test.screens.base.debugger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.screens.base.debugger.watchers.BodyWatcher;
import com.nzt.box.world.World;

public class WorldDebugger extends World {

    public Array<BodyWatcher> bodyWatchers = new Array<>();


    public WorldDebugger() {
    }

    @Override
    protected void moveBody(Body body) {
        for (BodyWatcher bodyWatcher : bodyWatchers) {
            if (bodyWatcher.stop(body) && bodyWatcher.breakAtMove) {
                logBreak(bodyWatcher, "move");
            }
        }
        super.moveBody(body);
    }

    @Override
    protected void checkBodyCollisions(Body bodyA) {
        for (BodyWatcher bodyWatcher : bodyWatchers) {
            if (bodyWatcher.stop(bodyA) && bodyWatcher.breakAtBodyCheckCollision) {
                logBreak(bodyWatcher, "move");
            }
        }
        super.checkBodyCollisions(bodyA);
    }

    @Override
    protected void checkFixtureCollision(Body bodyA, Fixture fixtureA) {
        for (BodyWatcher bodyWatcher : bodyWatchers) {
            if (bodyWatcher.stop(bodyA) && bodyWatcher.breakAtFixtureCheckCollision) {
                logBreak(bodyWatcher, "move");
            }
        }
        super.checkFixtureCollision(bodyA,fixtureA);
    }

    @Override
    protected void fixtureTestCollision(Fixture fixtureA, Fixture fixtureB) {
        super.fixtureTestCollision(fixtureA, fixtureB);
    }

    private void logBreak(BodyWatcher bodyWatcher, String cause) {
        Gdx.app.log("BreakPoint" + bodyWatcher.getClass().getSimpleName(), "onMove");
    }
}
