package com.nzt.box.test.screens.physx;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBoucingBalll extends Box2dTestScreen {
    public STBoucingBalll(FastTesterMain main) {
        super(main);

        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 50);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        world.addBody(body);

        createWallAroundScreen();

        body.setVelocity(100,100);
    }

    @Override
    public void doRender(float dt) {

    }

    @Override
    public String getExplication() {
        return "Ball rebound test";
    }
}
