package com.nzt.box.test.screens.w2d.math;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;
import com.nzt.gdx.utils.GdxUtils;

@TestScreenList(group = "math")
public class STQuadTreeAddRemove extends Box2dTestScreen {
    public STQuadTreeAddRemove(FastTesterMain main) {
        super(main);
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawQuadTreeData = true;
        debugSettings.drawQuadTree = true;
        debugSettings.drawCenter = false;
        debugSettings.drawVelocity = false;
        debugSettings.drawBodyUserData = false;
        debugSettings.drawBounds = true;
        Gdx.input.setInputProcessor(inputProcessor());

        infoMsg("Left/Right click for add and destroy");

        Rectangle rectScreen = GdxUtils.screenAsRectangle(new Rectangle(), true);
        world.data.quadTreeContainer.init(rectScreen, 3, 5);
    }

    int userDataCount = 1;

    public InputProcessor inputProcessor() {
        return new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = getClickPos(camera, screenX, screenY);
                if (button == LEFT_CLICK) {
                    boxSTHelp.createBall(10, boxSTHelp.basicDynamicBodyDef, clickPos,
                            v(0, 0), userDataCount++ + "");
                } else {
                    findAndRemoveBody(clickPos);
                }
                return false;
            }
        };
    }

    private void findAndRemoveBody(Vector2 position) {
        Array<Body> bodies = world.data.bodies;
        for (Body body : bodies) {
            for (Fixture<?> fixture : body.fixtures) {
                Rectangle boundingRectangle = fixture.getBoundingRectangle();
                if (boundingRectangle.contains(position)) {
                    world.removeBody(body);
                    return;
                }
            }
        }

    }

    @Override
    public String getTestExplication() {
        return "Add and remove in quad tree";
    }

    @Override
    public void doRender(float dt) {

    }
}
