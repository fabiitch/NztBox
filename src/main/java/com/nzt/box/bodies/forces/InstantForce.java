package com.nzt.box.bodies.forces;

import com.badlogic.gdx.math.Vector3;

public class InstantForce implements Force{
    @Override
    public void reset() {

    }

    @Override
    public boolean applyToBody(float dt, Vector3 forceOnBody) {
        return false;
    }
}
