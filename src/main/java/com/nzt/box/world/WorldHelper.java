package com.nzt.box.world;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeContainer;

public class WorldHelper {

    public int bodiesIdGenerator = 1;

    private World world;
    private QuadTreeContainer quadTreeContainer;

    public WorldHelper(World world) {
        super();
        this.world = world;
        this.quadTreeContainer = world.data.quadTreeContainer;
//		this.data = world.data;
    }


    public void addId(Body body) {
        if (bodiesIdGenerator == Integer.MAX_VALUE) {
            bodiesIdGenerator = 1;
        }
        body.id = bodiesIdGenerator++;
    }


}
