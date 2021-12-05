package com.nzt.box.test.s_try.w2d.collisions.twobody.rotation;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.test.s_try.w2d.BaseSTry2Body;
import com.nzt.gdx.input.impl.simple.KeysInputHandler;
import com.nzt.gdx.math.shapes.builders.PolygonBuilder;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.collision.rotation")
public class STryCollisionRotation2RectPolyDynamic extends BaseSTry2Body {
    public STryCollisionRotation2RectPolyDynamic(FastTesterMain main) {
        super(main, BodyType.Dynamic, BodyType.Dynamic);
        infoMsg("R => reset");
        reset();
    }

    public void reset() {
        body1.setPosition(0, 0);
        body1.angularVelocity = 50;
        body2.angularVelocity = 0;
        body2.setPosition(-100, 0);
    }

    @Override
    protected InputProcessor addInputListener() {
        return new KeysInputHandler() {
            @Override
            public boolean doKeyDown(int keycode) {
                if (keycode == Input.Keys.R)
                    reset();
                return false;
            }

            @Override
            public boolean doKeyUp(int keycode) {
                return false;
            }
        };
    }

    @Override
    protected BodyShape createBodyShape1() {
        return new PolygonShape(PolygonBuilder.rectangle(10, 300, true));
    }

    @Override
    protected BodyShape createBodyShape2() {
        return new PolygonShape(PolygonBuilder.rectangle(10, 300, true));
    }

    @Override
    public String getTestExplication() {
        return "Try collision on rotation with static";
    }
}
