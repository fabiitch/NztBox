package com.nzt.box.test.screens.w2d.collisions.twobody.forces.ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.box.test.screens.utils.BoxDebugUtils;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.forces")
/**
 * Set body pos in after click
 */
abstract class Base2BallCollision extends Box2dTestScreen {

    protected Body ball1, ball2;

    protected BodyDef bodyDefBall1, bodyDefBall2;

    public Base2BallCollision(FastTesterMain main) {
        super(main);
        infoMsg("Click for reset");
        Gdx.input.setInputProcessor(addInputListener());
        world.contactListener = addContactListener();

        HudDebug.addTopRight("beginContact", false);
        HudDebug.addTopRight("continueContact", false);
        HudDebug.addTopRight("endContact", false);

        bodyDefBall1 = boxSTHelp.basicDynamicBodyDef.cpy();
        bodyDefBall1.mass(getMass1()).transfert(getTransfert1()).restitution(getRestitution1());

        bodyDefBall2 = boxSTHelp.basicDynamicBodyDef.cpy();
        bodyDefBall1.mass(getMass2()).transfert(getTransfert2()).restitution(getRestitution2());
        create2Ball();

        BoxDebugUtils.toHud(ball1, HudDebugPosition.LEFT_MIDDLE);
        BoxDebugUtils.toHud(ball2, HudDebugPosition.RIGHT_MIDDLE);
    }

    protected abstract float getMass1();

    protected abstract float getMass2();

    protected abstract float getRestitution1();

    protected abstract float getRestitution2();

    protected abstract float getTransfert1();

    protected abstract float getTransfert2();

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

    protected void create2Ball() {
        ball1 = createBall(1, boxSTHelp.basicDynamicBodyDef);
        ball2 = createBall(2, boxSTHelp.basicDynamicBodyDef);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("velocity 1 ", ball1.velocity, HudDebugPosition.BOT_LEFT, Color.WHITE);
        debugMsg("velocity 2 ", ball2.velocity, HudDebugPosition.BOT_LEFT, Color.WHITE);
    }

    private InputProcessor addInputListener() {
        return new SimpleClickInputHandler() {
            private Vector2 clickPos = new Vector2(0, 0);

            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                getClickPos(camera, screenX, screenY, clickPos);
                afterClick(clickPos);
                return false;
            }
        };
    }

    protected Body createBall(int userData, BodyDef bodyDef) {
        Body body = new Body(bodyDef);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        fixture.userData = "" + userData;
        body.userData = "" + userData;
        world.addBody(body);
        return body;
    }
}
