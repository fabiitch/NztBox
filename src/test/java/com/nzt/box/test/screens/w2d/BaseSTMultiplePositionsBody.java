package com.nzt.box.test.screens.w2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public abstract class BaseSTMultiplePositionsBody extends Box2dTestScreen {

    private final static String KeyNbBodies = "BodyNumber";
    private final Array<Body> bodies;
    private final Array<Body> posTest = new Array<>();

    public BaseSTMultiplePositionsBody(FastTesterMain main) {
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

    public void afterClick(Vector2 pos) {



    }

    public abstract void doRenderM(float dt);

    @Override
    public void doRender(float dt) {
        HudDebug.update(KeyNbBodies, world.data.bodies.size);
    }

    public static class PosTest {
        public Vector2 pos = new Vector2();
        public int testNum;

        public Vector2 pos(float x, float y) {
            testNum++;
            return pos.set(x, y);
        }
    }
}

