package com.nzt.box.test.runnable.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;

/**
 * Pre remplir pour check after
 */
public class ForceDataResult {
    public Body body;
    public Vector2 velExpected;

    public ForceDataResult() {
    }
    public ForceDataResult setVelExpected(Vector2 velExpected) {
        this.velExpected = velExpected;
        return this;
    }
    public ForceDataResult setVelocityAfter(float x, float y) {
        this.velExpected = new Vector2(x,y);
        return this;
    }
}
