package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.SnapshotArray;

public class Body {

    public int id;
    public BodyType bodyType;
    public boolean bullet = false; //check continus deplacement for collision

    public SnapshotArray<Fixture> fixtures;

    public Vector3 position = new Vector3();
    public Vector3 forces = new Vector3();
    public Vector3 velocity = new Vector3();

    private Vector3 tmp = new Vector3();


    public float bouncing = 0;
    public float maxDstFixture;
    public boolean dirty;

    public Body(BodyType bodyType) {
        this.bodyType = bodyType;
        fixtures = new SnapshotArray<>();
    }

    public boolean move(float dt) {
        if (velocity.isZero())
            return false;
        position.add(tmp.set(velocity).scl(dt));
        updatePosition();
        return true;
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
        fixture.body = this;
        updatePosition();
        this.maxDstFixture = Math.max(this.maxDstFixture, fixture.bodyShape.maxDst);
    }

    public Vector2 getPosition(Vector2 position2D) {
        position2D.x = position.x;
        position2D.y = position.y;
        return position2D;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        updatePosition();
    }

    public void setPosition(Vector2 position) {
        this.setPosition(position.x, position.y);
    }

    public void setPosition(Vector3 position) {
        dirty = true;
        this.position.set(position);
        updatePosition();
    }

    public void updatePosition() {
        for (int i = 0, n = fixtures.size; i < n; i++) {
            Fixture fixture = fixtures.get(i);
            fixture.changeBodyPosition(position.x, position.y);
        }
    }

    public void setVelocity(Vector3 velocity) {
        velocity.set(velocity);
    }

    public void setVelocity(float x, float y, float z) {
        velocity.set(x, y, z);
    }

    public void setVelocity(Vector2 velocity) {
        velocity.set(velocity);
    }

    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }
}
