package com.nzt.box.test.screens.w2d.math;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.screens.base.ScreenWalls;
import com.nzt.box.test.screens.w2d.BaseSTMultipleBody;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "math")
public class STQuadTreeMove extends BaseSTMultipleBody {


    public STQuadTreeMove(FastTesterMain main) {
        super(main);

        Rectangle rectangle = ScreenWalls.getRectangle();
        world.data.quadTreeContainer.init(rectangle, 3, 10);
        createWallAroundScreen();
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawQuadTreeData = true;
        debugSettings.drawQuadTree = true;
        debugSettings.drawCenter = false;
        debugSettings.drawVelocity = false;
        debugSettings.drawBodyUserData = false;
        debugSettings.drawBounds = true;
        for (int i = 0; i < 10; i++) {
            createBody();
        }
        world.simulationRunning = false;
    }

    int userData = 1;

    private void createBody() {
        Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
        Vector2 pos = new Vector2();
        pos.x = MathUtils.random(-SCREEN_WITDH / 2 + 3, SCREEN_WITDH / 2 - 1);
        pos.y = MathUtils.random(-SCREEN_HEIGHT / 2 + 3, SCREEN_HEIGHT / 2 - 1);
        boxSTHelp.createBall(10, boxSTHelp.basicDynamicBodyDef, pos, velocity, userData++ + "");
    }

    @Override
    public String getTestExplication() {
        return null;
    }

    @Override
    public void doRenderM(float dt) {

    }
}
