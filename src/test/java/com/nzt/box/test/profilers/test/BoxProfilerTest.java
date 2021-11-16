package com.nzt.box.test.profilers.test;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.test.profilers.WorldProfiler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Simple test if profiler work
 */
public class BoxProfilerTest {


    @Test
    public void profilerTest() {
        try {
            WorldProfiler worldProfiler = new WorldProfiler();
            Body bodyCreated = new Body(BodyType.Static);
            worldProfiler.addBody(bodyCreated);
            Body bodyInWorld = worldProfiler.data.bodies.get(0);
            Assertions.assertTrue(bodyInWorld == bodyCreated);

//            Fixture fixtureA = new Fixture(new CircleShape(10));
//            body.addFixture(fixtureA);
//            Fixture fixtureB = new Fixture(new CircleShape(10));
//            body.addFixture(fixtureB);

        } catch (Exception e) {
            Assertions.fail("error", e);
        }

    }
}
