package com.nzt.box.test.screens.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nzt.box.debug.World2dDebugRenderer;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public abstract class Box2dTestScreen extends BoxTestScreen {

    public Box2dTestScreen(FastTesterMain main) {
        super(main);
        camera = new OrthographicCamera(SCREEN_WITDH, SCREEN_HEIGHT);
        camera.update();
        debugRenderer = new World2dDebugRenderer();
    }
}
