package com.nzt.box.test.screens.arch;

import com.nzt.gdx.main.AbstractMain;
import com.nzt.gdx.screen.BaseScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterScreenManager;

public class NztBoxScreenManager extends FastTesterScreenManager {
    public NztBoxScreenManager(BaseScreen<AbstractMain> screen) {
        super(screen);
    }

    @Override
    protected void doPause() {
        System.out.println("pause");
    }

}
