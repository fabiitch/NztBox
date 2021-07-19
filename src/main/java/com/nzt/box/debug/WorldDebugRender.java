package com.nzt.box.debug;

import com.badlogic.gdx.math.Matrix4;
import com.nzt.box.world.World;

public interface WorldDebugRender {

  void render(World world, Matrix4 combined);
}
