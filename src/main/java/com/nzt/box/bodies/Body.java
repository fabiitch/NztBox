package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.SnapshotArray;

public class Body {

    public BodyType bodyType;
    public boolean bullet = false; //check continus deplacement for collision


    public SnapshotArray<Fixture> fixtures;

    public Vector3 position = new Vector3();
    public Vector3 forces = new Vector3();
    public Vector3 velocity = new Vector3();

    private Vector3 tmp = new Vector3();

    public Body(BodyType bodyType) {
        this.bodyType = bodyType;
        fixtures = new SnapshotArray<>();
    }


    public void move(float dt) {
        if (!velocity.isZero()) {
            position.add(tmp.set(velocity).scl(dt));
            setPosition(position);
        }
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
        fixture.body = this;
    }

    public void setPosition(Vector2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
        setPosition(this.position);
    }

    public void setPosition(Vector3 position) {
        this.position.set(position);
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
