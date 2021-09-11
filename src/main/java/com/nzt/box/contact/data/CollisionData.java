package com.nzt.box.contact.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class CollisionData implements Pool.Poolable {

    public Vector2 reboundA = new Vector2();
    public Vector2 reboundB = new Vector2();

    public Vector2 forceOnA = new Vector2();
    public Vector2 forceOnB = new Vector2();

    public Vector2 normal = new Vector2();

    @Override
    public void reset() {
        reboundA.setZero();
        reboundB.setZero();

        forceOnA.setZero();
        forceOnB.setZero();

        normal.setZero();
    }

    @Override
    public String toString() {
        return "CollisionData{" +
                "reboundA=" + reboundA +
                ", reboundB=" + reboundB +
                ", forceOnA=" + forceOnA +
                ", forceOnB=" + forceOnB +
                ", normal=" + normal +
                '}';
    }
}
