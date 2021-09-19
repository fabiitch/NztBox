package com.nzt.box.test;

import com.nzt.box.test.screens.w2d.physx.bouncing.circle.STBoucingCircle;
import com.nzt.gdx.test.trials.tester.single.SingleScreenTestMain;

/**
 * Use it for Test one class with screenTestClass
 */
public class NztBoxTestStarter {
    private static final Class screenTestClass = STBoucingCircle.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        NztBoxTestListStarter.startLwjgl3(new SingleScreenTestMain(screenTestClass), witdh, height);
    }
}
