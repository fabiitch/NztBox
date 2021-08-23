package com.nzt.box.world;

import com.nzt.box.bodies.Body;

public class WorldHelper {

	private int idBodyCount = 0;

	private World world;
	private WorldData data;

	public WorldHelper(World world) {
		super();
		this.world = world;
//		this.data = world.data;
	}

	public void testContactBodies(Body bodyA, Body bodyB) {

	}

	public void addBody(Body body) {
		body.id = ++idBodyCount;
	}
}
