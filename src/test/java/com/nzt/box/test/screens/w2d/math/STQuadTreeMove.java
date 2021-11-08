package com.nzt.box.test.screens.w2d.math;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.screens.w2d.BaseSTMultipleBody;
import com.nzt.gdx.test.api.tester.GdxTestUtils;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "math")
public class STQuadTreeMove extends BaseSTMultipleBody {

    Rectangle rectangleScreen;

    public STQuadTreeMove(FastTesterMain main) {
        super(main);
        world.data.quadTreeContainer.init(new Rectangle(), 2, 4);
        createWallAroundScreen();
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawQuadTreeData = true;
        debugSettings.drawQuadTree = true;
        debugSettings.drawCenter = false;
        debugSettings.drawVelocity = false;
        debugSettings.drawBodyUserData = true;
        debugSettings.drawBounds = false;
        for (int i = 0; i < 30; i++) {
            createBody();
        }
        world.simulationRunning = true;
        rectangleScreen = GdxTestUtils.screenAsRectangle(false);
    }

    private void createBody() {
        Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
        Vector2 pos = new Vector2();
        pos.x = MathUtils.random(-SCREEN_WITDH / 2 + 3, SCREEN_WITDH / 2 - 1);
        pos.y = MathUtils.random(-SCREEN_HEIGHT / 2 + 3, SCREEN_HEIGHT / 2 - 1);
        boxSTHelp.createCircle(10, boxSTHelp.basicDynamicBodyDef, pos, velocity, null);
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
            if (rectangleScreen.contains(body.getPosition(tmp)))
                countBodyIn++;
        }
        debugMsg("Body in rect", countBodyIn);
    }
}
