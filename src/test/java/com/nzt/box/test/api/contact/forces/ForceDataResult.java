package com.nzt.box.test.api.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;

/**
 * Pre remplir pour check after
 */
public class ForceDataResult {
    public Body body;
    public Vector2 forceOn;
    public Vector2 velocityAfter;

    public ForceDataResult() {
    }

    public ForceDataResult setForceOn(Vector2 forceOn) {
        this.forceOn = forceOn;
        return this;
    }

    public ForceDataResult setVelocityAfter(Vector2 velocityAfter) {
        this.velocityAfter = velocityAfter;
        return this;
    }

}
