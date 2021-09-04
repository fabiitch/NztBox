package com.nzt.box.contact.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class CollisionData implements Pool.Poolable {

    public Vector2 reboundA = new Vector2();
    public Vector2 reboundB = new Vector2();
    public Vector2 normal = new Vector2();

    public boolean isBlockingContact = false;

    @Override
    public void reset() {
        reboundA.setZero();
        reboundB.setZero();
        normal.setZero();
        isBlockingContact = false;
    }
}
