package com.nzt.box.test.api.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;

/**
 * Pre remplir pour check after
 */
public class ForceDataTest {
    public Body body;
    public Vector2 forceOn;
    public Vector2 velocityAfter;

    public ForceDataTest() {
    }

    public void set(Vector2 forceOn, Vector2 velocityAfter) {
        this.forceOn = forceOn;
        this.velocityAfter = velocityAfter;
    }
}
