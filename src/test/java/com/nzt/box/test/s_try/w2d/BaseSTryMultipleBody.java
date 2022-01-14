package com.nzt.box.test.s_try.w2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.mains.FastTesterMain;

public abstract class BaseSTryMultipleBody extends BaseBox2DSTry {

    private final static String KeyNbBodies = "Bodies number";
    private final Array<Body> bodies;
    private final Array<Body> posTest = new Array<>();

    public BaseSTryMultipleBody(FastTesterMain main) {
        super(main);
        this.bodies = world.data.bodies;
        HudDebug.addBotLeft(KeyNbBodies, "INITTTTTTT");

        new SimpleClickInputHandler() {
            private Vector2 clickPos = new Vector2(0, 0);

            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                getClickPos(camera, screenX, screenY, clickPos);
                afterClick(clickPos);
                return false;
            }
        };
    }

    public abstract void doRenderM(float dt);

    @Override
    public void doRender(float dt) {
        HudDebug.update(KeyNbBodies, world.data.bodies.size);
        doRenderM(dt);
    }

}

