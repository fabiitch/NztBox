package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.SnapshotArray;

public class Body {

    public BodyType bodyType;
    public SnapshotArray<Fixture> fixtures;

    public Vector3 position = new Vector3();
    public Vector3 forces = new Vector3();
    public Vector3 velocity = new Vector3();

    public Body(BodyType bodyType) {
        this.bodyType = bodyType;
        fixtures = new SnapshotArray<>();
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
    }

    public void changePosition(Vector2 position) {
    }

    public void changePosition(Vector3 position) {
        for (int i = 0, n = fixtures.size; i < n; i++) {
            Fixture fixture = fixtures.get(i);
            fixture.changeBodyPosition(position.x, position.y);
        }
    }
}
