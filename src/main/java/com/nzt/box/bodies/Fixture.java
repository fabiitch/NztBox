package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.BodyShape;

public class Fixture {
    public BodyShape bodyShape;

    public Fixture(BodyShape bodyShape) {
        this.bodyShape = bodyShape;
    }

    public void changeBodyPosition(float x, float y) {
        bodyShape.changeBodyPosition(x, y);
    }

    public void changeBodyPosition(Vector2 position) {
        bodyShape.changeBodyPosition(position);
    }
}
