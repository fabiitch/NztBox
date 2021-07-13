package com.nzt.box;

import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.bodies.Body;

public class World {

    public SnapshotArray<Body> bodies;

    public World() {
        this.bodies = new SnapshotArray<>();
    }

    public void step(float dt) {

    }
}
