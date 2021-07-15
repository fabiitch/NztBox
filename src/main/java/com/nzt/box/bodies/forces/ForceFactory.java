package com.nzt.box.bodies.forces;

import com.badlogic.gdx.utils.Pools;

public class ForceFactory {

    public static InterpolationDurationForce getDurationForce(float x, float y, float z){
        InterpolationDurationForce obtain = Pools.obtain(InterpolationDurationForce.class);
        return obtain;
    }
}
