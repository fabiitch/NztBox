package com.nzt.box.world;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactBody;

public class WorldData {
	
	private World world;
	 public SnapshotArray<Body> bodies;
	public ObjectMap<Body, ContactBody> mapContact;

	public WorldData(World world) {
		super();
		this.world = world;
		this.mapContact = new ObjectMap<>();
	}

}
