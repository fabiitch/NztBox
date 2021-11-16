package com.nzt.box.test.profilers;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;

public class BodyProfiler extends Body {

    public int boudingBoxCompute = 0;
    public WorldProfiler worldProfiler;

    public BodyProfiler(Body body) {
        super(body.bodyType);
    }

    public void initProfiler(WorldProfiler worldProfiler) {
        this.worldProfiler = worldProfiler;
        for (Fixture f : fixtures) {
            removeFixture(f);
            addFixture(f);
        }
    }

    @Override
    public void addFixture(Fixture fixture) {
        FixtureProfiler fixtureProfiler = (FixtureProfiler) fixture;
        fixtureProfiler.worldProfiler = this.worldProfiler;
        super.addFixture(fixture);
    }

    @Override
    public void computeBoudingBox() {
        boudingBoxCompute++;
        worldProfiler.bodyBoudingBoxCompute++;
        super.computeBoudingBox();
    }
}
