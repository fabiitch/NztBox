package com.nzt.box.test.base;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.nzt.box.world.World;
import com.nzt.box.debug.WorldDebugRender;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;

abstract class BoxTestScreen extends TestScreen {
    public static final float SCREEN_WITDH = 1080;
    public static final float SCREEN_HEIGHT = 720;

    protected Camera camera;
    public World world;
    public WorldDebugRender debugRenderer;

    public BoxTestScreen(FastTesterMain main) {
        super(main);
        world = new World();
    }

    public void infoMsg(String msg) {
        HudDebug.addTopLeft("", msg);
    }

    public void debugMsg(String tag, Object value) {
        debugMsg(tag, value, HudDebugPosition.BOT_LEFT, Color.WHITE);
    }

    public void debugMsg(String tag, Object value, int positionOnStage) {
        debugMsg(tag, value, positionOnStage, Color.WHITE);
    }

    public void debugMsg(String tag, Object value, int positionOnStage, Color color) {
        if (HudDebug.exist(tag)) {
            if (HudDebug.getColor(tag) != color)
                HudDebug.changeColor(tag, color);
            HudDebug.update(tag, value);
        } else {
            HudDebug.add(tag, value, positionOnStage, color);
        }
    }

    public abstract void doRender(float dt);

    @Override
    public final void renderTestScreen(float dt) {
        camera.update();
        world.step(dt);
        debugRenderer.render(world, camera.combined);
        doRender(dt);
    }

    @Override
    public void disposeTestScreen() {

    }

//    public void createWallAroundScreen() {
//        /**
//         * D-----C
//         * -------
//         * A-----B
//         */
//        float aX = -SCREEN_WITDH / 2 + 3, aY = -SCREEN_HEIGHT / 2 + 1;
//        float bX = SCREEN_WITDH / 2 - 1, bY = -SCREEN_HEIGHT / 2 + 1;
//        float cX = SCREEN_WITDH / 2 - 1, cY = SCREEN_HEIGHT / 2 - 1;
//        float dX = -SCREEN_WITDH / 2 + 1, dY = SCREEN_HEIGHT / 2 - 1;
//
//
//        Body botBody = new Body<>();
//        botBody.bodyType = BodyType.Static;
//        botBody.userData = "WallHorizontalBot";
//        NzLine botHorizontal = new NzLine(aX, aY, bX, bY);
//        botBody.addFixture(new Fixture(botHorizontal));
//        world.addBody(botBody);
//
//        Body topBody = new Body<>();
//        topBody.bodyType = BodyType.Static;
//        topBody.userData = "WallHorizontalTop";
//        NzLine topHorizontal = new NzLine(dX, dY, cX, cY);
//        topBody.addFixture(new Fixture(topHorizontal));
//        world.addBody(topBody);
//
//        Body rightBody = new Body<>();
//        rightBody.bodyType = BodyType.Static;
//        rightBody.userData = "WallVerticalRight";
//        NzLine rightVertical = new NzLine(bX, bY, cX, cY);
//        rightBody.addFixture(new Fixture(rightVertical));
//        world.addBody(rightBody);
//
//
//        Body leftBody = new Body<>();
//        leftBody.bodyType = BodyType.Static;
//        leftBody.userData = "WallbotHorizontal";
//        NzLine leftVertical = new NzLine(aX, aY, dX, dY);
//        leftBody.addFixture(new Fixture(leftVertical));
//        world.addBody(leftBody);
//    }
}
