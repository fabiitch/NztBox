package com.nzt.box.test.screens.w2d.physx.balls.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.box.test.screens.base.utils.BoxDebugUtils;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.balls.collision")
public abstract class BaseBallCollision extends Box2dTestScreen {

    protected Body ball1, ball2;
    private Vector2 clickPos = new Vector2(200, 0);

    final float RESTITUTION = 0;

    public BaseBallCollision(FastTesterMain main) {
        super(main);
        infoMsg("Click for reset");
        createBodies();
        Gdx.input.setInputProcessor(addInputListener());
        world.contactListener = addContactListener();

        HudDebug.addTopRight("beginContact", false);
        HudDebug.addTopRight("continueContact", false);
        HudDebug.addTopRight("endContact", false);
        HudDebug.addRightMiddle("RESTITUTION", RESTITUTION);
    }

    private ContactListener addContactListener() {
        return new ContactListener() {
            @Override
            public void beginContact(ContactFixture contactFixture) {
                HudDebug.update("beginContact", true);
                BoxDebugUtils.toHud(1, contactFixture.collisionData, HudDebugPosition.BOT_LEFT, Color.RED);
            }

            @Override
            public void endContact(ContactFixture contactFixture) {
                HudDebug.update("endContact", true);
            }

            @Override
            public void continueContact(ContactFixture contactFixture) {
                HudDebug.update("continueContact", true);
            }

            @Override
            public void preSolve(ContactFixture contactFixture) {

            }

        };
    }

    protected void createBodies() {
        ball1 = createBall(0);
        ball2 = createBall(1);
        ball1.restitution = RESTITUTION;
        ball2.restitution = RESTITUTION;
        world.addBody(ball1);
        world.addBody(ball2);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("velocity A ", ball1.velocity, HudDebugPosition.LEFT_MIDDLE, Color.WHITE);
        debugMsg("velocity B ", ball2.velocity, HudDebugPosition.LEFT_MIDDLE, Color.WHITE);
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
        body.userData = "" + userData;
        return body;
    }

    public abstract void afterClick(Vector2 clickPos);
}
