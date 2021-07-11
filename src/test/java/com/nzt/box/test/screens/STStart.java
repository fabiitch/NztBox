package com.nzt.box.test.screens;

import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D")
public class STStart extends TestScreen {
    public STStart(FastTesterMain main) {
        super(main);
    }

    @Override
    public String getExplication() {
        return "hollaaa";
    }

    @Override
    public void renderTestScreen(float dt) {

    }

    @Override
    public void disposeTestScreen() {

    }
}
