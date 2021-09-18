package com.nzt.box.test.screens.w2d.collisions.forces;

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

@TestScreenList(group = "2D.collision.forces")
public abstract class BaseBallCollision extends Box2dTestScreen {

    protected Body ball1, ball2;
    private Vector2 clickPos = new Vector2(200, 0);


    public BaseBallCollision(FastTesterMain main) {
        super(main);
        infoMsg("Click for reset");
        createBodies();
        Gdx.input.setInputProcessor(addInputListener());
        world.contactListener = addContactListener();

        HudDebug.addTopRight("beginContact", false);
        HudDebug.addTopRight("continueContact", false);
        HudDebug.addTopRight("endContact", false);

        HudDebug.addLeftMiddle("MASS_1", getMass1());
        HudDebug.addLeftMiddle("RESTITUTION_1", getRestitution1());
        HudDebug.addLeftMiddle("TRANSFERT_1", getTransfert1());
        HudDebug.addLeftMiddle("-----------", "-----",Color.RED);
        HudDebug.addLeftMiddle("MASS_2", getMass2());
        HudDebug.addLeftMiddle("RESTITUTION_2", getRestitution2());
        HudDebug.addLeftMiddle("TRANSFERT_2", getTransfert2());
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

    protected void createBodies() {
        ball1 = createBall(1, getMass1(), getRestitution1(), getTransfert1());
        ball2 = createBall(2, getMass2(), getRestitution2(), getTransfert2());
        world.addBody(ball1);
        world.addBody(ball2);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("velocity 1 ", ball1.velocity, HudDebugPosition.BOT_LEFT, Color.WHITE);
        debugMsg("velocity 2 ", ball2.velocity, HudDebugPosition.BOT_LEFT, Color.WHITE);
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

    protected Body createBall(int userData, float mass, float restitution, float transfert) {
        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        body.mass = mass;
        body.userData = "" + userData;
        body.restitution = restitution;
        body.transfert = transfert;
        fixture.userData = "" + userData;
        return body;
    }

    public abstract void afterClick(Vector2 clickPos);
}
