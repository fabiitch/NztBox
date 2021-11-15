package com.nzt.box.test.profilers;

import com.nzt.box.world.World;

public class WorldProfiler {

    public int iterationPerFrame = 0;
    public int collisionComparaison = 0;
    public int testContact = 0;


    public World world;

    public WorldProfiler() {
        this.world = new World() {
            @Override
            public void step(float dt) {
                iterationPerFrame = 0;
                collisionComparaison = 0;
                testContact = 0;
                super.step(dt);
            }

            @Override
            protected void iteration() {
                iterationPerFrame++;
                super.iteration();
            }
        };
    }
}
