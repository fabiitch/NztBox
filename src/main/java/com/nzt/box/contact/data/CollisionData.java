package com.nzt.box.contact.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class CollisionData implements Pool.Poolable {

    public Vector2 collisionPoint = new Vector2();
    public Vector2 normal = new Vector2();

    public Vector2 newVelA = new Vector2();
    public Vector2 newVelB = new Vector2();



    @Override
    public void reset() {
        collisionPoint.setZero();
        normal.setZero();

        newVelA.setZero();
        newVelB.setZero();
    }

    @Override
    public String toString() {
        return "CollisionData{" +
                "collisionPoint=" + collisionPoint +
                ", normal=" + normal +
                ", newVelA=" + newVelA +
                ", newVelB=" + newVelB +
                '}';
    }
}
