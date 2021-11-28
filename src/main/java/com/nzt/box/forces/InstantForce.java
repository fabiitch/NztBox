package com.nzt.box.forces;

import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;

public class InstantForce implements Force {
    Vector3 force = new Vector3();

    @Override
    public void reset() {
        force.setZero();
    }

    @Override
    public boolean applyToBody(float dt, Body body) {
        body.velocity.add(force);
        return true;
    }
}
