package com.nzt.box.test.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.nzt.box.debug.WorldDebugRender;
import com.nzt.box.test.screens.utils.BoxSTHelp;
import com.nzt.box.world.World;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.debug.perf.PerformanceFrame;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;

abstract class BoxTestScreen extends TestScreen {
    public static final float SCREEN_WITDH = 1080;
    public static final float SCREEN_HEIGHT = 720;

    protected Camera camera;
    public World world;
    public WorldDebugRender debugRenderer;
    public ScreenWalls screenWalls;
    public BoxSTHelp boxSTHelp;

    public boolean simulationRunning = true;

    private final static String KEY_WORLD_RUN = "SimulationRun";
    private final static String KEY_WORLD_CALCUL_TIME = "BoxCalculTime";

    public BoxTestScreen(FastTesterMain main) {
        super(main);
        world = new World();
        boxSTHelp = new BoxSTHelp(world);
        HudDebug.addTopLeft(KEY_WORLD_RUN, "" +
                "Press Space to pause/run simulation", simulationRunning, Color.RED);

        PerformanceFrame.add(KEY_WORLD_CALCUL_TIME);
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
            HudDebug.update(KEY_WORLD_RUN, simulationRunning);
        }
        PerformanceFrame.startAction(KEY_WORLD_CALCUL_TIME);
        if (simulationRunning) {
            world.step(dt);
        }
        PerformanceFrame.endAction(KEY_WORLD_CALCUL_TIME);
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
