package com.nzt.box.test.screens.physx.balls.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.balls.collision")
public abstract class BaseBallCollision extends Box2dTestScreen {

    protected Body ball1, ball2;
    private Vector2 clickPos = new Vector2(200, 0);

    public BaseBallCollision(FastTesterMain main) {
        super(main);
        ball1 = createBall(0);
        ball2 = createBall(1);
        world.addBody(ball1);
        world.addBody(ball2);
        Gdx.input.setInputProcessor(addInputListener());
    }

    @Override
    public void doRender(float dt) {

    }

    private InputProcessor addInputListener() {
        return new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                getClickPos(camera, screenX, screenY, clickPos);
                afterClick(clickPos);
                return false;
            }
        };
    }

    protected Body createBall(int userData) {
        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        body.userData = userData;
        return body;
    }

    public abstract void afterClick(Vector2 clickPos);
}
