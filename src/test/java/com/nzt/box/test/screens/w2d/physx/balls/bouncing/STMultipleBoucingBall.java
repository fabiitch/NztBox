package com.nzt.box.test.screens.w2d.physx.balls.bouncing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.balls.bouncing")
public class STMultipleBoucingBall extends Box2dTestScreen {
    private Array<Body> allBalls = new Array<>();
    private Vector2 tmpPos = new Vector2();
    private Rectangle rectScreen;
    private int userDataBall = 1;

    public STMultipleBoucingBall(FastTesterMain main) {
        super(main);
        debugRenderer.debugSettings.drawVelocity = false;
        createWallAroundScreen();
        rectScreen = new Rectangle(-SCREEN_WITDH / 2, -SCREEN_HEIGHT / 2, SCREEN_WITDH, SCREEN_HEIGHT);
        for (int i = 0; i < 10; i++) {
            Body ball = createBall(userDataBall);
            allBalls.add(ball);
            userDataBall++;
        }
        debugMsg("Balls created", allBalls.size);

        infoMsg("Click to add 10 balls");
        Gdx.input.setInputProcessor(new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                for (int i = 0; i < 10; i++) {
                    Body ball = createBall(userDataBall);
                    allBalls.add(ball);
                    userDataBall++;
                }
                debugMsg("Balls created", allBalls.size);
                return false;
            }
        });

    }

    private Body createBall(int userData) {
        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture<CircleShape> fixture = new Fixture<CircleShape>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        body.userData = userData;
        Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
        body.setVelocity(velocity);
        return body;
    }

    @Override
    public void doRender(float dt) {
        Array<Body> bodiesNew = new Array<>();

        int bodyOut = 0;
        for (int i = 0; i < allBalls.size; i++) {
            Body body = allBalls.get(i);
            body.getPosition(tmpPos);
            if (rectScreen.contains(tmpPos)) {
                bodiesNew.add(body);
            } else {
                bodyOut++;
            }
        }
        debugMsg("Balls in rect", bodiesNew.size);
        debugMsg("Balls out of rect", bodyOut);
        bodiesNew.shrink();
        bodiesNew.clear();
    }

    @Override
    public String getTestExplication() {
        return "Multiple circle collion";
    }

    @Override
    public void disposeTestScreen() {
        super.disposeTestScreen();
    }
}
