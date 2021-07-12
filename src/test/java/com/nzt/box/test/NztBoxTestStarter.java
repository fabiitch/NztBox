package com.nzt.box.test;

import com.nzt.box.test.screens.STStart;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.main.StarterTestConfig;

/**
 * Use it for Test one class with screenTestClass
 */
public class NztBoxTestStarter {
    private static final Class screenTestClass = STStart.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        NztBoxTestListStarter.startLwjgl3(new FastTesterMain(screenTestClass), witdh, height);
    }
}
