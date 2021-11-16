package com.nzt.box.test.profilers;

import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;

public class FixtureProfiler extends Fixture {

    public int computeBoudingBox = 0;
    public int testContact = 0;
    public WorldProfiler worldProfiler;

    public FixtureProfiler(BodyShape bodyShape) {
        super(bodyShape);
    }

    @Override
    public Rectangle computeBoundingRect() {
        computeBoudingBox++;
        worldProfiler.fixtureBoudingBoxCompute++;
        return super.computeBoundingRect();
    }

    @Override
    public boolean testContact(Fixture fixtureB) {
        testContact++;
        worldProfiler.fixtureTestContact++;
        return super.testContact(fixtureB);
    }
}
