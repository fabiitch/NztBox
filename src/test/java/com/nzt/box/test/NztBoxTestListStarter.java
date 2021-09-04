package com.nzt.box.test;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.nzt.box.test.screens.arch.NztBoxSelectorTestMain;
import com.nzt.gdx.main.AbstractMain;
import com.nzt.gdx.test.trials.NztTestListStarter;
import com.nzt.gdx.test.trials.tester.selector.screen.ScreenSelectorTestMain;

/**
 * Open Test chooser
 */
public class NztBoxTestListStarter {

    private static final int witdh = 800;
    private static final int height = 500;

    public static void startLwjgl3(AbstractMain main, int witdh, int height) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Tester Lwjgl3");
        configuration.setWindowedMode(witdh, height);
        configuration.setWindowIcon("box.png");
        configuration.useVsync(true);
        new Lwjgl3Application(main, configuration);
    }

    public static void startLwjgl3FullScreen(AbstractMain main) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Tester Lwjgl3");
        configuration.useVsync(true);
        configuration.setWindowedMode(witdh, height);
        configuration.setWindowIcon("box.png");
        new Lwjgl3Application(main, configuration);
    }

    public static void main(String[] args) {
        startLwjgl3(new NztBoxSelectorTestMain(NztTestListStarter.class,
                        "com.nzt.box.test"),
                witdh, height);
    }
}
