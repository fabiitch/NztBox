package com.nzt.box.test.screens.base.debugger.watchers;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.test.screens.base.debugger.BreakPoint;

public class BodyWatcher implements BreakPoint {

    public Body body;

    public boolean breakAtMove, breakAtBodyCheckCollision, breakAtFixtureCheckCollision;

    public Array<Body> bodyCheck = new Array<>();

    public BodyWatcher() {

    }

    public boolean stop(Body body) {
        return body == this.body;
    }

    @Override
    public String type() {
        return "BodyWatcher";
    }


}
