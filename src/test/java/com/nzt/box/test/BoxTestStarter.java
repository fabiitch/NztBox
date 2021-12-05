package com.nzt.box.test;

import com.nzt.box.test.s_try.w2d.math.STryQuadTreeAddRemove;
import com.nzt.gdx.test.utils.archi.mains.mains.SingleScreenTestMain;

/**
 * Use it for Test one class with screenTestClass
 */
public class BoxTestStarter {
    private static final Class screenTestClass = STryQuadTreeAddRemove.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        BoxTestListStarter.startLwjgl3(new SingleScreenTestMain(screenTestClass), witdh, height);
    }
}
