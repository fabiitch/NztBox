package com.nzt.box.test.s_try.w2d.boudingbox;

import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.boudingbox")
abstract class BaseSTryBoudingBox extends BaseBox2DSTry {
    public BaseSTryBoudingBox(FastTesterMain main) {
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
