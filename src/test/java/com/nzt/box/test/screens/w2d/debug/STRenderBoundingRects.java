package com.nzt.box.test.screens.w2d.debug;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.screens.w2d.BaseSTMultipleBody;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.debug")
public class STRenderBoundingRects extends BaseSTMultipleBody {
    public STRenderBoundingRects(FastTesterMain main) {
        super(main);
        createWallAroundScreen();
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;

        debugSettings.drawBounds = true;
        debugSettings.drawCenter = true;
        debugSettings.drawContactPoint = false;
        debugSettings.drawBodyUserData = false;
        debugSettings.drawInactive = false;
        debugSettings.drawVelocity = false;

        for (int i = 0; i < 15; i++) {
            Vector2 position = new Vector2();
            position.x = MathUtils.random(-SCREEN_WITDH / 2 + 3, SCREEN_WITDH / 2 - 1);
            position.y = MathUtils.random(-SCREEN_HEIGHT / 2 + 3, SCREEN_HEIGHT / 2 - 1);
            Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
            boxSTHelp.createCircle(10, boxSTHelp.basicDynamicBodyDef, position, velocity, i + "");
        }
    }

    @Override
    public void doRenderM(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Test draw boundingRect";
    }
}
