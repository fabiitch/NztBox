package com.nzt.box.test.screens.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.render.g2d.Box2DDebugRenderer;
import com.nzt.box.test.screens.utils.InputCamera2DMover;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public abstract class Box2dTestScreen extends BoxTestScreen {
    public InputCamera2DMover inputCamera2DMover;

    public Box2dTestScreen(FastTesterMain main) {
        super(main);
        this.camera = new OrthographicCamera(SCREEN_WITDH, SCREEN_HEIGHT);
        this.camera.update();
        this.debugRenderer = new Box2DDebugRenderer();
        addInputProcessor(inputCamera2DMover = new InputCamera2DMover((OrthographicCamera) this.camera));
    }

    public void afterClick(Vector2 clickPos) {
        //redefine
    }

    @Override
    public final void renderTestScreen(float dt) {
        inputCamera2DMover.updateCamera();
        super.renderTestScreen(dt);
    }
}
