package com.nzt.box.bodies.collisions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.bodies.Fixture;

public class Collision implements Pool.Poolable {
    public Fixture fixtureA, fixtureB;
    public Vector2 point;

    public Collision() {
    }

    public static Collision getNew(Fixture fixtureA, Fixture fixtureB, Vector2 point) {
        Collision collision = Pools.obtain(Collision.class);
        collision.fixtureA = fixtureA;
        collision.fixtureB = fixtureB;
        collision.point = point;
        return collision;
    }

    @Override
    public void reset() {
        fixtureA = null;
        fixtureB = null;
        point.setZero();
    }
}
