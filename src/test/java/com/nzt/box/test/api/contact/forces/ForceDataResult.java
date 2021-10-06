package com.nzt.box.test.api.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;

/**
 * Pre remplir pour check after
 */
public class ForceDataResult {
    public Body body;
    public Vector2 velocityAfter;

    public ForceDataResult() {
    }
    public ForceDataResult setVelocityAfter(Vector2 velocityAfter) {
        this.velocityAfter = velocityAfter;
        return this;
    }
    public ForceDataResult setVelocityAfter(float x, float y) {
        this.velocityAfter = new Vector2(x,y);
        return this;
    }
}
