package com.nzt.box.test.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.nzt.box.debug.WorldDebugRender;
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
    public ScreenWalls screenWalls;

    public boolean simulationRunning = true;


    public BoxTestScreen(FastTesterMain main) {
        super(main);
        world = new World();
        HudDebug.addTopLeft("SimulationRun", "" +
                "Press Space to pause/run simulation", simulationRunning, Color.RED);
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
                HudDebug.updateColor(tag, color);
            HudDebug.update(tag, value);
        } else {
            HudDebug.add(tag, value, positionOnStage, color);
        }
    }

    public abstract void doRender(float dt);

    @Override
    public final void renderTestScreen(float dt) {
        camera.update();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            simulationRunning = !simulationRunning;
            HudDebug.update("SimulationRun", simulationRunning);
        }
        if (simulationRunning)
            world.step(dt);
        debugRenderer.render(world, camera.combined);
        doRender(dt);
    }

    @Override
    public void disposeTestScreen() {
        debugRenderer.dispose();
    }

    public void createWallAroundScreen() {
        screenWalls = new ScreenWalls(world);
    }
}
