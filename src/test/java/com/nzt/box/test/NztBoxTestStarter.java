package com.nzt.box.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.nzt.gdx.main.AbstractMain;
import com.nzt.gdx.test.trials.NztTestListStarter;
import com.nzt.gdx.test.trials.tester.selector.ScreenSelectorTestMain;

public class NztBoxTestStarter {

    private static final int witdh = 800;
    private static final int height = 500;

    public static void startLwjgl3(AbstractMain main, int witdh, int height) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Tester Lwjgl3");
        configuration.setWindowedMode(witdh, height);
        configuration.setWindowIcon("box.png");
        new Lwjgl3Application(main, configuration);
    }

    public static void main(String[] args) {
        startLwjgl3(new ScreenSelectorTestMain(NztTestListStarter.class, "com.nzt.box.test"),
                witdh, height);
    }
}
