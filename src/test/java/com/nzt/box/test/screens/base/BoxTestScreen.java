package com.nzt.box.test.screens.base;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.debug.WorldDebugRender;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.world.World;
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
        debugRenderer.dispose();
    }

    public void createWallAroundScreen() {
        /**
         * D-----C
         * -------
         * A-----B
         */
        float aX = -SCREEN_WITDH / 2 + 3, aY = -SCREEN_HEIGHT / 2 + 1;
        float bX = SCREEN_WITDH / 2 - 1, bY = -SCREEN_HEIGHT / 2 + 1;
        float cX = SCREEN_WITDH / 2 - 1, cY = SCREEN_HEIGHT / 2 - 1;
        float dX = -SCREEN_WITDH / 2 + 1, dY = SCREEN_HEIGHT / 2 - 1;


        Body botBody = new Body(BodyType.Static);
//        botBody.userData = "WallHorizontalBot";
        RectangleShape shapeBot = new RectangleShape(SCREEN_WITDH, 10);
        botBody.addFixture(new Fixture(shapeBot));
        world.addBody(botBody);
        botBody.setPosition(0, aY);

        Body topBody = new Body(BodyType.Static);
//        topBody.userData = "WallHorizontalTop";
        RectangleShape shapeTop = new RectangleShape(SCREEN_WITDH, 10);
        topBody.addFixture(new Fixture(shapeTop));
        world.addBody(topBody);
        topBody.setPosition(0, cY);
//
        Body rightBody = new Body(BodyType.Static);
//        rightBody.userData = "WallVerticalRight";
        RectangleShape shapeRight = new RectangleShape(10, SCREEN_HEIGHT);
        rightBody.addFixture(new Fixture(shapeRight));
        world.addBody(rightBody);
        rightBody.setPosition(-SCREEN_WITDH / 2, 0);


        Body leftBody = new Body(BodyType.Static);
//        leftBody.userData = "WallbotHorizontal";
        RectangleShape shapeLeft = new RectangleShape(10, SCREEN_HEIGHT);
        leftBody.addFixture(new Fixture(shapeLeft));
        world.addBody(leftBody);
        leftBody.setPosition(SCREEN_WITDH / 2, 0);
    }
}
