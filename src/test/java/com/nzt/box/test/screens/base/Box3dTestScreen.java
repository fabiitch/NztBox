package com.nzt.box.test.screens.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nzt.box.debug.World3dDebugRenderer;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public abstract class Box3dTestScreen extends BoxTestScreen {
    public Box3dTestScreen(FastTesterMain main) {
        super(main);
        camera = new OrthographicCamera(SCREEN_WITDH, SCREEN_HEIGHT);
        debugRenderer = new World3dDebugRenderer();
    }
}
