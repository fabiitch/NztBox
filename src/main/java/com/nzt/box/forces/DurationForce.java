package com.nzt.box.forces;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Body;

//TODO
public class DurationForce implements Force, Pool.Poolable {
    public Vector3 force;
    public float time;
    public float timeElapsed;

    public DurationForce() {
        force = new Vector3();
    }

    public boolean valid() {
        return time > timeElapsed;
    }

    public boolean applyToBody(float dt, Body forceOnBody) {
        timeElapsed += dt;
        if (valid())
            return false;
//        forceOnBody.add(force);
        return true;
    }

    @Override
    public void reset() {
        force.setZero();
        time = 0;
        timeElapsed = 0;
    }
}
