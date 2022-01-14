package com.nzt.box.test.s_try.w2d.collisions.ccd;

import com.nzt.box.bodies.Body;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.box.test.s_try.utils.BoxDebugUtils;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.collision.ccd")
public class STryContinousCollisionDetection extends BaseBox2DSTry {
    public STryContinousCollisionDetection(FastTesterMain main) {
        super(main);
        Body ball = boxSTHelp.createDynamicCircle(10, v(0, 0), v(5000000, 0));
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
