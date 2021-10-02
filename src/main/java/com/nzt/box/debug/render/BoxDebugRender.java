package com.nzt.box.debug.render;

import com.badlogic.gdx.math.Matrix4;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.world.World;


public abstract class BoxDebugRender {

    public BoxDebugSettings debugSettings;

    public BoxDebugRender() {
        this(new BoxDebugSettings());
    }

    public BoxDebugRender(BoxDebugSettings debugSettings) {
        this.debugSettings = debugSettings;

    }

    public abstract void render(World world, Matrix4 combined);

    public abstract void dispose();
}
