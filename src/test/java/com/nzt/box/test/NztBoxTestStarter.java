package com.nzt.box.test;

import com.nzt.box.test.screens.physx.STMultipleBoucingBall;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

/**
 * Use it for Test one class with screenTestClass
 */
public class NztBoxTestStarter {
    private static final Class screenTestClass = STMultipleBoucingBall.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        NztBoxTestListStarter.startLwjgl3(new FastTesterMain(screenTestClass), witdh, height);
    }
}