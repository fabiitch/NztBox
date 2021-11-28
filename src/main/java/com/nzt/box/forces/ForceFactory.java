package com.nzt.box.forces;

import com.badlogic.gdx.utils.Pools;

public class ForceFactory {

    public static InterpolationDurationForce getDurationForce(float x, float y, float z) {
        InterpolationDurationForce obtain = Pools.obtain(InterpolationDurationForce.class);
        return obtain;
    }

    public static InstantForce getInstant(float x, float y, float z) {
        InstantForce obtain = Pools.obtain(InstantForce.class);
        obtain.force.set(x, y, z);
        return obtain;
    }
}
