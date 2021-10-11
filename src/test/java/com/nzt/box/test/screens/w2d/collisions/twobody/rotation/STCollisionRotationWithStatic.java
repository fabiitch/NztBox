package com.nzt.box.test.screens.w2d.collisions.twobody.rotation;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.test.screens.w2d.BaseST2Body;
import com.nzt.gdx.input.impl.simple.KeysInputHandler;
import com.nzt.gdx.math.shapes.builders.PolygonBuilder;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.rotation")
public class STCollisionRotationWithStatic extends BaseST2Body {
    protected Vector2 posBodyA = new Vector2(-0, 0);
    protected Vector2 posBodyB = new Vector2(-100, 0);

    public STCollisionRotationWithStatic(FastTesterMain main) {
        super(main, BodyType.Dynamic, BodyType.Static);
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
                if(keycode == Input.Keys.R)
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
        return new PolygonShape(PolygonBuilder.rectangle(10, 200, true));
    }

    @Override
    protected BodyShape createBodyShape2() {
        return new CircleShape(50);
    }

    @Override
    public String getTestExplication() {
        return "Try collision on rotation with static";
    }
}