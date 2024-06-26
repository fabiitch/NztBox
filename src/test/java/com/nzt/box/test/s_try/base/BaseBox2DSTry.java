package com.nzt.box.test.s_try.base;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.render.g2d.Box2DDebugRenderer;
import com.nzt.box.test.s_try.utils.BoxCamera2DController;
import com.nzt.gdx.mains.FastTesterMain;

public abstract class BaseBox2DSTry extends BaseBoxSTry {
    public BoxCamera2DController inputCamera2DMover;

    public BaseBox2DSTry(FastTesterMain main) {
        super(main);
        this.camera = new OrthographicCamera(SCREEN_WITDH, SCREEN_HEIGHT);
        this.camera.update();
        this.debugRenderer = new Box2DDebugRenderer();
        addInputProcessor(inputCamera2DMover = new BoxCamera2DController((OrthographicCamera) this.camera));
    }

    public void afterClick(Vector2 clickPos) {
        //redefine
    }

    @Override
    public final void renderTestScreen(float dt) {
        inputCamera2DMover.updateCameraPosition();
        super.renderTestScreen(dt);
    }
}
