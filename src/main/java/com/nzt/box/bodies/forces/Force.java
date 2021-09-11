package com.nzt.box.bodies.forces;

import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Body;

public interface Force extends Pool.Poolable {

    /**
     * @param dt
     * @param body
     * @return true when force is done
     */
    boolean applyToBody(float dt, Body body);
}
