package com.nzt.box.test.screens.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.render.g2d.Box2DDebugRenderer;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public abstract class Box2dTestScreen extends BoxTestScreen {

    public Box2dTestScreen(FastTesterMain main) {
        super(main);
        camera = new OrthographicCamera(SCREEN_WITDH, SCREEN_HEIGHT);
        camera.update();
        debugRenderer = new Box2DDebugRenderer();
    }

    public void afterClick(Vector2 clickPos){
        //redefine
    }
}
