package com.nzt.box.world;

import com.nzt.box.bodies.Body;

public class WorldHelper {

    private int idBodyCount = 0;


    public void addBody(Body body) {
        body.id = ++idBodyCount;
    }
}
