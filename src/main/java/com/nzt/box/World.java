package com.nzt.box;

import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

public class World {

    public SnapshotArray<Body> bodies;

    public World() {
        this.bodies = new SnapshotArray<>();
    }

    public void step(float dt) {
        bodies.begin();
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (body.bodyType != BodyType.Static) {
                body.move(dt);
                checkCollision(body);
            }
        }
        bodies.end();
    }

    public void checkCollision(Body body) {
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body bodyTest = bodies.get(i);
            if (body != bodyTest) {
                body.testContact(bodyTest);
            }
        }
    }
}
