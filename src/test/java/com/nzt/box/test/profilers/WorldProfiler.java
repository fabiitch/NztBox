package com.nzt.box.test.profilers;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.world.World;

public class WorldProfiler extends World {

    public int iterationPerFrame = 0;

    public int bodyBoudingBoxCompute = 0;
    public int fixtureBoudingBoxCompute = 0;

    public int collisionComparaison = 0;
    public int fixtureTestContact = 0;

    public WorldProfiler(float stepTime) {
        super(stepTime);
    }

    public WorldProfiler() {
        super();
    }

    @Override
    public void addBody(Body body) {
        body = new BodyProfiler(body);
        ((BodyProfiler) body).initProfiler(this);
        super.addBody(body);
    }

    @Override
    public void step(float dt) {
        iterationPerFrame = 0;
        super.step(dt);
    }

    @Override
    protected void iteration() {
        iterationPerFrame++;

        bodyBoudingBoxCompute = 0;
        fixtureBoudingBoxCompute = 0;

        fixtureTestContact = 0;
        collisionComparaison = 0;
        super.iteration();
    }

    @Override
    protected void checkFixtureCollision(Body bodyA, Fixture fixtureA, Fixture fixtureB) {
        collisionComparaison++;
        super.checkFixtureCollision(bodyA, fixtureA, fixtureB);
    }
}
