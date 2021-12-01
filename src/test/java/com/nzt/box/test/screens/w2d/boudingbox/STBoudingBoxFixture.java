package com.nzt.box.test.screens.w2d.boudingbox;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STBoudingBoxFixture extends BaseSTBoudingBox {
    public STBoudingBoxFixture(FastTesterMain main) {
        super(main);
        for (int i = 0; i < 15; i++) {
            Vector2 position = new Vector2();
            position.x = MathUtils.random(-SCREEN_WITDH / 2 + 3, SCREEN_WITDH / 2 - 1);
            position.y = MathUtils.random(-SCREEN_HEIGHT / 2 + 3, SCREEN_HEIGHT / 2 - 1);
            Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
            boxSTHelp.createCircle(10, boxSTHelp.basicDynamicBodyDef, position, velocity, i + "");
        }
    }


    @Override
    public String getTestExplication() {
        return "Test draw boudingbox fixture";
    }
}
