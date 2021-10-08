package com.nzt.box.world;


import com.nzt.box.bodies.Body;

public class WorldHelper {

    public int bodiesIdGenerator = 1;

    private World world;
    private WorldData data;

    public WorldHelper(World world) {
        super();
        this.world = world;
//		this.data = world.data;
    }


    public void addId(Body body) {
        if (bodiesIdGenerator == Integer.MAX_VALUE) {
            bodiesIdGenerator = 1;
        }
        body.id = bodiesIdGenerator++;
    }


}
