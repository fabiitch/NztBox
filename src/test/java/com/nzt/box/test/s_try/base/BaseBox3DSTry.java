package com.nzt.box.test.s_try.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nzt.box.debug.render.g3d.Box3DDebugRenderer;
import com.nzt.gdx.mains.FastTesterMain;

public abstract class BaseBox3DSTry extends BaseBoxSTry {
    public BaseBox3DSTry(FastTesterMain main) {
        super(main);
        camera = new OrthographicCamera(SCREEN_WITDH, SCREEN_HEIGHT);
        debugRenderer = new Box3DDebugRenderer();
    }
}
