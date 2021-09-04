package com.nzt.box.test.screens.arch;

import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterScreenManager;

public class NztBoxTestMain extends FastTesterMain {
    public NztBoxTestMain(Class screenClass) {
        super(screenClass);
    }

    @Override
    public FastTesterScreenManager createScreenManager() {
        return new NztBoxScreenManager(returnScreenToLaunch());
    }

}
