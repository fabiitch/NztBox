package com.nzt.box.test.screens.arch;

import com.nzt.gdx.test.trials.tester.archi.main.FastTesterScreenManager;
import com.nzt.gdx.test.trials.tester.selector.screen.STSelectorScreen;
import com.nzt.gdx.test.trials.tester.selector.screen.ScreenSelectorTestMain;

public class NztBoxSelectorTestMain extends ScreenSelectorTestMain {
    public NztBoxSelectorTestMain(Class screenClass, String packagePath) {
        super(screenClass, packagePath);
    }

    @Override
    public FastTesterScreenManager createScreenManager() {
        selectorScreenTest = new STSelectorScreen(this, rootCase);
        return new NztBoxScreenManager(selectorScreenTest);
    }
}
