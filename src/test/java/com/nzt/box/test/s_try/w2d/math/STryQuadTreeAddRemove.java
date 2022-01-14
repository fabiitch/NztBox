package com.nzt.box.test.s_try.w2d.math;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.box.test.s_try.utils.BoxCamera2DController;
import com.nzt.gdx.input.utils.InputUtils;
import com.nzt.gdx.test.utils.GdxTestUtils;
import com.nzt.gdx.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "math")
public class STryQuadTreeAddRemove extends BaseBox2DSTry {

    public STryQuadTreeAddRemove(FastTesterMain main) {
        super(main);
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawQuadTreeData = true;
        debugSettings.drawQuadTree = true;
        debugSettings.drawCenter = false;
        debugSettings.drawVelocity = false;
        debugSettings.drawBodyUserData = true;
        debugSettings.drawBoudingsBoxFixtures = true;

        infoMsg("arrow for move, mouse for zoom");
        infoMsg("Left for create rect");
        infoMsg("Right Click destroy");
        Rectangle rectScreen = GdxTestUtils.screenAsRectangle(true);
        world.data.quadTreeContainer.init(rectScreen, 3, 5);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                addInputProcessor(inputProcessor());
            }
        }, 0.1f);
        debugMsg("maxValues :", world.data.quadTreeContainer.maxValues);
        debugMsg("maxDepth :", world.data.quadTreeContainer.maxDepth);
    }

    Rectangle rectBodyCreation = null;
    Rectangle rectBodyDestruction = null;
    Vector2 startPositionRect = new Vector2();


    public InputProcessor inputProcessor() {
        return new BoxCamera2DController((OrthographicCamera) this.camera) {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                startPositionRect.set(clickPos);
                if (button == InputUtils.LEFT_CLICK) {
                    rectBodyCreation = new Rectangle();
                } else if (button == InputUtils.RIGHT_CLICK) {
                    rectBodyDestruction = new Rectangle();
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                if (rectBodyCreation != null) {
                    rectBodyCreation.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rectBodyCreation.merge(clickPos);
                    boxSTHelp.createRect(rectBodyCreation, boxSTHelp.basicStaticBodyDef, new Vector2(), null);
                    rectBodyCreation = null;
                } else if (rectBodyDestruction != null) {
                    rectBodyDestruction.set(startPositionRect.x, startPositionRect.y, 0, 0);
                    rectBodyDestruction.merge(clickPos);
                    findAndRemoveBody(rectBodyDestruction);
                    rectBodyDestruction = null;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 clickPos = InputUtils.getClickPos(camera, screenX, screenY);
                Rectangle rect = rectBodyCreation != null ? rectBodyCreation : rectBodyDestruction;
                rect.set(startPositionRect.x, startPositionRect.y, 0, 0);
                rect.merge(clickPos);
                return false;
            }


        };
    }

    private void findAndRemoveBody(Rectangle rectangle) {
        QuadTree quadTree = world.data.quadTreeContainer.root.getQuadTree(rectangle);
        if (quadTree == null)
            return;
        Array<Fixture> valuesAndSub = quadTree.getValuesAndSub(quadTree.container.poolFixtureArray.obtain());
        for (int i = 0; i < valuesAndSub.size; i++) {
            Fixture fixture = valuesAndSub.get(i);
            if (rectangle.contains(fixture.body.getPosition(new Vector2()))) {
                world.removeBody(fixture.body);
            }
        }
        quadTree.container.poolFixtureArray.free(valuesAndSub);
    }

    @Override
    public String getTestExplication() {
        return "Add and remove in quad tree";
    }

    @Override
    public void doRender(float dt) {
        if (rectBodyCreation != null || rectBodyDestruction != null) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin();
            if (rectBodyCreation != null) {
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(rectBodyCreation);
            }
            if (rectBodyDestruction != null) {
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.rect(rectBodyDestruction);
            }
            shapeRenderer.end();
        }
    }
}
