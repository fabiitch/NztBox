package com.nzt.box.test.screens.w2d.move;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.bodies.forces.ForceFactory;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleMvtInputController;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.forces")
public class STIntantForces extends Box2dTestScreen {
    Body body;

    public STIntantForces(FastTesterMain main) {
        super(main);
        body = new Body(BodyType.Dynamic);
        Fixture fixture = new Fixture(new CircleShape(50));
        body.addFixture(fixture);
        world.addBody(body);

        infoMsg("Use ZQSD for move body");
        Gdx.input.setInputProcessor(addInput());
    }

    public InputProcessor addInput() {
        return new SimpleMvtInputController() {
            @Override
            public void up() {
                body.addForce(ForceFactory.getInstant(0, 100, 0));
            }

            @Override
            public void down() {
                body.addForce(ForceFactory.getInstant(0, -100, 0));
            }

            @Override
            public void left() {
                body.addForce(ForceFactory.getInstant(-100, 0, 0));
            }

            @Override
            public void right() {
                body.addForce(ForceFactory.getInstant(100, 0, 0));
            }
        };
    }


    @Override
    public void doRender(float dt) {

    }


    @Override
    public String getTestExplication() {
        return "Move body using intant forces";
    }
}
