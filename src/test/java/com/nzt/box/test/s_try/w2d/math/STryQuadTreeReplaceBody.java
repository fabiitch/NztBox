package com.nzt.box.test.s_try.w2d.math;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "math")
public class STryQuadTreeReplaceBody extends BaseBox2DSTry {
    public STryQuadTreeReplaceBody(FastTesterMain main) {
        super(main);
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawQuadTreeData = true;
        debugSettings.drawQuadTree = true;
        debugSettings.drawBoudingsBoxFixtures = true;
        boxSTHelp.createCircle(100, boxSTHelp.basicDynamicBodyDef, new Vector2(), new Vector2(), null);
        boxSTHelp.createCircle(100, boxSTHelp.basicDynamicBodyDef, new Vector2(), new Vector2(), null);
        addInputProcessor(input());
    }

    private InputProcessor input() {
        return new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                boxSTHelp.createCircle(100, boxSTHelp.basicDynamicBodyDef, new Vector2(), new Vector2(), null);
                return false;
            }
        };
    }

    @Override
    public void doRender(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Test if quadtree grow when body spawn and replace";
    }
}
