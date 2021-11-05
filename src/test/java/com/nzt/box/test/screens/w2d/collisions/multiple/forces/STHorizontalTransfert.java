package com.nzt.box.test.screens.w2d.collisions.multiple.forces;

import com.nzt.box.test.screens.utils.BoxDebugUtils;
import com.nzt.box.test.screens.w2d.BaseSTMultipleBody;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.forces.multiples")
public class STHorizontalTransfert extends BaseSTMultipleBody {
    public STHorizontalTransfert(FastTesterMain main) {
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
