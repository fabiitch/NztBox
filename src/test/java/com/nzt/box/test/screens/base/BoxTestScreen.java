package com.nzt.box.test.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.nzt.box.debug.render.BoxDebugRender;
import com.nzt.box.test.screens.utils.BoxSTHelp;
import com.nzt.box.world.World;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.debug.perf.PerformanceFrame;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;
import com.nzt.gdx.utils.GdxUtils;

abstract class BoxTestScreen extends TestScreen {
    public static final float SCREEN_WITDH = 1080;
    public static final float SCREEN_HEIGHT = 720;


    protected Camera camera;
    public World world;
    public BoxDebugRender debugRenderer;
    public ScreenWalls screenWalls;

    public BoxSTHelp boxSTHelp;
    public boolean blockAtContact = false;

    private final static String KEY_WORLD_RUN = "SimulationRun";
    private final static String KEY_CALCUL_PERF = "BoxCalculTime";
    private final static String KEY_RENDER_PERF = "BoxRenderTime";


    public BoxTestScreen(FastTesterMain main) {
        super(main);
        world = new World();
        boxSTHelp = new BoxSTHelp(world);
        HudDebug.addTopLeft("JavaHeap", GdxUtils.getHeapMb() + " MB");
        HudDebug.addTopLeft(KEY_WORLD_RUN, "" +
                "Press Space to pause/run simulation", world.simulationRunning, Color.WHITE);

        PerformanceFrame.add(KEY_CALCUL_PERF);
        PerformanceFrame.add(KEY_RENDER_PERF);
    }

    @Override
    public final void renderTestScreen(float dt) {
        HudDebug.update("JavaHeap", GdxUtils.getHeapMb() + " MB");
        camera.update();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            world.simulationRunning = !world.simulationRunning;
            HudDebug.update(KEY_WORLD_RUN, world.simulationRunning);
        }
        PerformanceFrame.startAction(KEY_CALCUL_PERF);
        world.step(dt);
        PerformanceFrame.endAction(KEY_CALCUL_PERF);

        PerformanceFrame.startAction(KEY_RENDER_PERF);
        debugRenderer.render(world, camera.combined);
        PerformanceFrame.endAction(KEY_RENDER_PERF);
        doRender(dt);
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
    public void disposeTestScreen() {
        debugRenderer.dispose();
    }

    public void createWallAroundScreen() {
        this.screenWalls = new ScreenWalls(world);
    }
}
