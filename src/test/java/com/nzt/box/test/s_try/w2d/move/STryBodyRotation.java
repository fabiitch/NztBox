package com.nzt.box.test.s_try.w2d.move;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.move")
public class STryBodyRotation extends BaseBox2DSTry {
    public STryBodyRotation(FastTesterMain main) {
        super(main);
        Vector2[] vertices = new Vector2[]{v(0, 0), v(100, 0), v(100, 80), v(-10, 50)};
        Body polyBody = boxSTHelp.createPoly(vertices, boxSTHelp.basicDynamicBodyDef, v(0, 0), v(0, 0), "Polygon");
        polyBody.angularVelocity = 50;
    }

    @Override
    public void doRender(float dt) {

    }

    @Override
    public String getTestExplication() {
        return null;
    }
}
