package com.nzt.box.test.screens.w2d.boudingbox;

import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.boudingbox")
abstract class BaseSTBoudingBox extends Box2dTestScreen {
    public BaseSTBoudingBox(FastTesterMain main) {
        super(main);
        createWallAroundScreen();
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawBoudingsBoxFixtures = true;
        debugSettings.drawBoudingsBoxBodies = true;
        debugSettings.drawCenter = false;
        debugSettings.drawContactPoint = false;
        debugSettings.drawBodyUserData = false;
        debugSettings.drawInactive = false;
        debugSettings.drawVelocity = false;
    }

    @Override
    public void doRender(float dt) {

    }
}
