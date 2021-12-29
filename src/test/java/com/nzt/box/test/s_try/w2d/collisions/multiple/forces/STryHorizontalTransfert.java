package com.nzt.box.test.s_try.w2d.collisions.multiple.forces;

import com.nzt.box.test.s_try.utils.BoxDebugUtils;
import com.nzt.box.test.s_try.w2d.BaseSTryMultipleBody;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.collision.forces.multiples")
public class STryHorizontalTransfert extends BaseSTryMultipleBody {
    public STryHorizontalTransfert(FastTesterMain main) {
        super(main);
        boxSTHelp.createDynamicCircle(10, V2.v(-300, 0), V2.v(100, 0));
        boxSTHelp.createDynamicCircle(10, V2.v(-0, 0), V2.v(0, 0));
        boxSTHelp.createDynamicCircle(10, V2.v(200, 0), V2.v(0, 0));

        BoxDebugUtils.toHud(boxSTHelp.basicDynamicBodyDef, "BallDef", HudDebugPosition.BOT_LEFT);
    }

    @Override
    public void doRenderM(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Test force transfert";
    }
}
