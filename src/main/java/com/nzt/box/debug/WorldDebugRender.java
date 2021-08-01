package com.nzt.box.debug;

import com.badlogic.gdx.math.Matrix4;
import com.nzt.box.world.World;


public abstract class WorldDebugRender {

    public BoxDebugSettings debugSettings;

    public WorldDebugRender() {
        this(new BoxDebugSettings());
    }

    public WorldDebugRender(BoxDebugSettings debugSettings) {
        this.debugSettings = debugSettings;

    }

    public abstract void render(World world, Matrix4 combined);

    public abstract void dispose();
}
