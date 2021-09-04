package com.nzt.box.bodies.forces;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

public interface Force extends Pool.Poolable {

    /**
     * @param dt
     * @param forceOnBody
     * @return true while force is valid and active
     */
    boolean applyToBody(float dt, Vector3 forceOnBody);
}
