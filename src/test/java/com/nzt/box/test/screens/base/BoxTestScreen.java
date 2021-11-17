package com.nzt.box.test.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.nzt.box.debug.render.BoxDebugRender;
import com.nzt.box.test.screens.utils.BoxSTHelp;
import com.nzt.box.world.World;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.debug.perf.PerformanceFrame;
import com.nzt.gdx.test.api.tester.GdxTestUtils;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;
import com.nzt.gdx.utils.GdxUtils;

abstract class BoxTestScreen extends TestScreen {
    public static final float SCREEN_WITDH = 1080;
    public static final float SCREEN_HEIGHT = 720;

    protected Camera camera;
    public World world;
    public BoxDebugRender debugRenderer;
    public RectangleWalls rectangleWalls;

    public BoxSTHelp boxSTHelp;
    public boolean blockAtContact = false;

    public InputMultiplexer inputMultiplexer;

    private final static String Key_WorldRunning = "SimulationRunnig";
    private final static String Key_CalculPerf = "BoxCalcul%";
    private final static String Key_CalculPercent = "BoxCalcul";
    private final static String Key_RenderPercent = "BoxRender%";


    public final static String Key_TimeStep = "BoxTimeStep";
    public final static String Key_TimeIteration = "BoxTimeIteration";
    public final static String Key_NbIteration = "BoxNbIteration";
    public final static String Key_FixtureContactCheck = "BoxFixtureContactCheck";


    public BoxTestScreen(FastTesterMain main) {
        super(main);
        world = new World();
        boxSTHelp = new BoxSTHelp(world);
        HudDebug.addTopLeft("JavaHeap", GdxUtils.getHeapMb() + " MB");
        HudDebug.addTopLeft(Key_WorldRunning, "Press Space to pause/run simulation", world.simulationRunning, Color.WHITE);

        HudDebug.addBotLeft(Key_CalculPercent, 1000 + " ms");
        PerformanceFrame.add(Key_CalculPerf);
        PerformanceFrame.add(Key_RenderPercent);

        HudDebug.addTopRight(Key_TimeStep, world.profiler.timerStep.average);
        HudDebug.addTopRight(Key_TimeIteration, world.profiler.timerIteration.average);
        HudDebug.addTopRight(Key_NbIteration, world.profiler.iterationPerStep);
        HudDebug.addTopRight(Key_FixtureContactCheck, world.profiler.fixtureContactCheck.iteration);

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void addInputProcessor(InputProcessor inputProcessor) {
        this.inputMultiplexer.addProcessor(inputProcessor);
    }

    public abstract void doRender(float dt);

    @Override
    public void renderTestScreen(float dt) {
        camera.update();
        HudDebug.update("JavaHeap", GdxUtils.getHeapMb() + " MB");
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            world.simulationRunning = !world.simulationRunning;
            HudDebug.update(Key_WorldRunning, world.simulationRunning);
        }
        PerformanceFrame.startAction(Key_CalculPerf);
        world.step(dt);
        PerformanceFrame.endAction(Key_CalculPerf);

        PerformanceFrame.startAction(Key_RenderPercent);
        debugRenderer.render(world, camera.combined);
        PerformanceFrame.endAction(Key_RenderPercent);

        HudDebug.update(Key_TimeStep, world.profiler.timerStep.average);
        HudDebug.update(Key_TimeIteration, world.profiler.timerIteration.average);
        HudDebug.update(Key_NbIteration, world.profiler.iterationPerStep);
        HudDebug.update(Key_FixtureContactCheck, world.profiler.fixtureContactCheck.iteration);
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

    @Override
    public void disposeTestScreen() {
        debugRenderer.dispose();
    }

    public void createWallAroundScreen() {
        this.rectangleWalls = new RectangleWalls(GdxTestUtils.screenAsRectangle(camera, true),
                200, world);
    }

}
