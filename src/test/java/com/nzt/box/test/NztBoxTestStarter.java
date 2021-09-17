package com.nzt.box.test;

import com.nzt.box.test.screens.w2d.physx.balls.bouncing.STBoucingBalll;
import com.nzt.gdx.test.trials.tester.single.SingleScreenTestMain;

/**
 * Use it for Test one class with screenTestClass
 */
public class NztBoxTestStarter {
    private static final Class screenTestClass = STBoucingBalll.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        NztBoxTestListStarter.startLwjgl3(new SingleScreenTestMain(screenTestClass), witdh, height);
    }
}
