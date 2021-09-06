package com.nzt.box.test.screens.w2d.move;

import com.badlogic.gdx.InputProcessor;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleMvtInputController;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.forces")
public class STIntantForces extends Box2dTestScreen {
    public STIntantForces(FastTesterMain main) {
        super(main);
        Body body = new Body(BodyType.Dynamic);
        Fixture fixture = new Fixture(new CircleShape(50));
        body.addFixture(fixture);
        world.addBody(body);


        infoMsg("Use ZQSD for move body");

    }

    public InputProcessor addInput(){
        return new SimpleMvtInputController() {
            @Override
            public void up() {
            }

            @Override
            public void down() {

            }

            @Override
            public void left() {

            }

            @Override
            public void right() {

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
