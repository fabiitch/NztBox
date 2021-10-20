package com.nzt.box.test.screens.w2d.collisions.ccd;

import com.nzt.box.bodies.Body;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.box.test.screens.utils.BoxDebugUtils;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.ccd")
public class STContinousCollisionDetection extends Box2dTestScreen {
    public STContinousCollisionDetection(FastTesterMain main) {
        super(main);
        Body ball = boxSTHelp.createDynamicBall(10, v(0, 0), v(5000000, 0));
        Body wall = boxSTHelp.createRect(50, 500, boxSTHelp.basicStaticBodyDef.cpy().restitution(0), v(50, 0), v(0, 0), "Wall");

        BoxDebugUtils.toHud(ball, HudDebugPosition.BOT_LEFT);
        BoxDebugUtils.toHud(wall, HudDebugPosition.BOT_LEFT);
        world.simulationRunning = false;
    }

    @Override
    public void doRender(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Test CCD";
    }
}
