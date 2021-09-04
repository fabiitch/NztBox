package com.nzt.box.test;

import com.nzt.box.test.screens.arch.NztBoxTestMain;
import com.nzt.box.test.screens.physx.balls.bouncing.STMultipleBoucingBall;

/**
 * Use it for Test one class with screenTestClass
 */
public class NztBoxTestStarter {
    private static final Class screenTestClass = STMultipleBoucingBall.class;

    private static final int witdh = 800;
    private static final int height = 500;

    public static void main(String[] args) {
        NztBoxTestListStarter.startLwjgl3(new NztBoxTestMain(screenTestClass), witdh, height);
    }
}
