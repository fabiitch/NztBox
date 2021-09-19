package com.nzt.box.test.screens.w2d.physx.bouncing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.bouncing")
public abstract class BaseSTBouncingMassBodies extends Box2dTestScreen {
    private Array<Body> allBody = new Array<>();
    private Vector2 tmpPos = new Vector2();
    private Rectangle rectScreen;
    private int userDataBall = 1;

    public BaseSTBouncingMassBodies(FastTesterMain main) {
        super(main);
        debugRenderer.debugSettings.drawVelocity = false;
        createWallAroundScreen();
        rectScreen = new Rectangle(-SCREEN_WITDH / 2, -SCREEN_HEIGHT / 2, SCREEN_WITDH, SCREEN_HEIGHT);
        for (int i = 0; i < 10; i++) {
            Body ball = createBody();
        }
        debugMsg("Balls created", allBody.size);

        infoMsg("Click to add 10 balls");
        Gdx.input.setInputProcessor(new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                for (int i = 0; i < 10; i++) {
                    createBody();
                }
                debugMsg("Balls created", allBody.size);
                return false;
            }
        });
    }


    protected abstract BodyDef bodyDef();

    protected abstract BodyShape bodyShape();

    private Body createBody() {
        Body body = new Body(bodyDef());
        BodyShape shape = bodyShape();
        Fixture<?> fixture = new Fixture<>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        body.userData = userDataBall++;
        Vector2 pos = new Vector2();
        pos.x = MathUtils.random(-SCREEN_WITDH / 2 + 3, SCREEN_WITDH / 2 - 1);
        pos.y = MathUtils.random(-SCREEN_HEIGHT / 2 + 3, SCREEN_HEIGHT / 2 - 1);
        body.setPosition(pos);
        Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
        body.setVelocity(velocity);
        allBody.add(body);
        return body;
    }

    @Override
    public void doRender(float dt) {
        Array<Body> bodiesNew = new Array<>();

        int bodyOut = 0;
        for (int i = 0; i < allBody.size; i++) {
            Body body = allBody.get(i);
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

}
