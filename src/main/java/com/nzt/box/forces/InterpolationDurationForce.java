package com.nzt.box.forces;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import com.nzt.gdx.math.Percentage;

public class InterpolationDurationForce implements Pool.Poolable {
    public Interpolation interpolation;
    public Vector3 forceStart;
    public Vector3 forceEnd;
    public float time;
    public float timeElapsed;

    public InterpolationDurationForce() {
        interpolation = Interpolation.linear;
        forceStart = new Vector3();
        forceEnd = new Vector3();
    }

    public boolean valid() {
        return time > timeElapsed;
    }

    public boolean applyToBody(float dt, Vector3 forceOnBody) {
        timeElapsed += dt;
        if (!valid())
            return false;
        float alpha = Percentage.getAlpha(timeElapsed, time);
        float x = interpolation.apply(forceStart.x, forceEnd.x, alpha);
        float y = interpolation.apply(forceStart.y, forceEnd.y, alpha);
        float z = interpolation.apply(forceStart.z, forceEnd.z, alpha);

        forceOnBody.add(x, y, z);
        return true;
    }

    @Override
    public void reset() {
        interpolation = Interpolation.linear;
        forceStart.setZero();
        forceEnd.setZero();
        time = 0;
        timeElapsed = 0;
    }
}
