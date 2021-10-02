package com.nzt.box.test.screens.w2d.collisions.multiple.mass;

import com.nzt.box.test.screens.w2d.BaseSTMultiplePositionsBody;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STHorizontalTransfert extends BaseSTMultiplePositionsBody {
    public STHorizontalTransfert(FastTesterMain main) {
        super(main);
    }

    @Override
    public void doRenderM(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Horziontal force transfert";
    }
}
