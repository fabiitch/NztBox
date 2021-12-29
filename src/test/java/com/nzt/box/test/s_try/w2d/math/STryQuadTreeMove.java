package com.nzt.box.test.s_try.w2d.math;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.s_try.w2d.BaseSTryMultipleBody;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.test.utils.GdxTestUtils;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "math")
public class STryQuadTreeMove extends BaseSTryMultipleBody {

    Rectangle rectangleScreen;
    OrthographicCamera camera;

    public STryQuadTreeMove(FastTesterMain main) {
        super(main);
        this.camera = (OrthographicCamera) super.camera;
        infoMsg("Arrow for move, click for recreate wallScreens");
        infoMsg("T for add bodies");
        world.data.quadTreeContainer.init(new Rectangle(), 2, 4);
        createWallAroundScreen();
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawQuadTreeData = true;
        debugSettings.drawQuadTree = true;
        debugSettings.drawCenter = false;
        debugSettings.drawVelocity = false;
        debugSettings.drawBodyUserData = true;
        debugSettings.drawBoudingsBoxFixtures = false;

        create10Bodies();
        world.simulationRunning = true;
        rectangleScreen = GdxTestUtils.screenAsRectangle(camera, true);

        addInputProcessor(input());
    }

    public InputAdapter input() {
        return new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.T)
                    create10Bodies();
                return super.keyDown(keycode);
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                rectangleWalls.removeWalls();
                Rectangle rect = RectangleUtils.createFromCenter(0, 0,
                        camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
                rectangleWalls.create(rect, 200);
                return super.touchUp(screenX, screenY, pointer, button);
            }
        };
    }

    private void create10Bodies() {
        for (int i = 0; i < 10; i++) {
            Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
            Vector2 pos = new Vector2();
            pos.x = MathUtils.random(-camera.viewportWidth * camera.zoom / 2 + 3, camera.viewportWidth * camera.zoom / 2 / 2 - 3);
            pos.y = MathUtils.random(-camera.viewportHeight * camera.zoom / 2 + 3, camera.viewportHeight * camera.zoom / 2 - 3);
            boxSTHelp.createCircle(10, boxSTHelp.basicDynamicBodyDef, pos, velocity, null);
        }
    }

    @Override
    public String getTestExplication() {
        return null;
    }

    int countBodyIn = 0;
    Vector2 tmp = new Vector2();

    @Override
    public void doRenderM(float dt) {
        countBodyIn = 0;
        for (Body body : world.data.bodies) {
            Vector2 position = body.getPosition(tmp);
            if (rectangleScreen.contains(position))
                countBodyIn++;
        }
        debugMsg("Body in rect", countBodyIn);
    }
}
