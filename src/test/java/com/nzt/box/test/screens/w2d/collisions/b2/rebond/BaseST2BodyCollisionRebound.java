package com.nzt.box.test.screens.w2d.collisions.b2.rebond;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.screens.w2d.BaseST2Body;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.rebound")
public abstract class BaseST2BodyCollisionRebound<S1 extends BodyShape, S2 extends BodyShape> extends BaseST2Body<S1, S2> {

    private Vector2 target = new Vector2();
    boolean drawContactInfo = false;

    public BaseST2BodyCollisionRebound(FastTesterMain main) {
        super(main, BodyType.Dynamic, BodyType.Static);
        infoMsg("Press F for change shape moving");
        infoMsg("Right Click for change pos position");
        infoMsg("Left click for change target");
        infoMsg("Press A for run simulation after collision");
        body2.setPosition(0, 0);
        changePosition(new Vector2(300, 500));
        body2.getPosition(target);
        changeWorldContactListener();
    }

    private void changeWorldContactListener() {
        ContactListener contactListener = new ContactListener() {

            @Override
            public void beginContact(ContactFixture contactBody) {
                drawContactInfo = true;
                debugMsg("Collision", true, HudDebugPosition.BOT_RIGHT);
                System.out.println("beginContact");
                doBeginContact();
            }

            @Override
            public void endContact(ContactFixture contactBody) {
                debugMsg("Collision", false, HudDebugPosition.BOT_RIGHT);
                System.out.println("endContact");
            }

            @Override
            public void continueContact(ContactFixture contactBody) {

            }

            @Override
            public void preSolve(ContactFixture contactBody) {

            }
        };
        world.contactListener = contactListener;
    }

    protected abstract void doBeginContact();

    public abstract void renderContactInfo(float dt);

    private void changeType() {
        bodyMove.bodyType = BodyType.Static;
        bodyMove.setVelocity(0, 0);
        Body targetBody = bodyMove == body1 ? body2 : body1;
        targetBody.bodyType = BodyType.Dynamic;
        bodyMove = targetBody;
        bodyStatic = bodyMove == body1 ? body2 : body1;
        changePosition(bodyMove.getPosition(new Vector2()));
    }

    private void changePosition(Vector2 pos) {
        Body targetBody = bodyMove == body1 ? body2 : body1;

        targetBody.setPosition(0, 0);
        bodyMove.setPosition(pos);
        changeTarget();
    }

    private void changeTarget() {
        Vector2 dir = V2.directionTo(bodyMove.getPosition(new Vector2()), target, new Vector2());
        bodyMove.setVelocity(dir.scl(100));
    }

    @Override
    public void doRender(float dt) {
        super.doRender(dt);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(target.x - 10, target.y, target.x + 10, target.y);
        shapeRenderer.line(target.x, target.y - 10, target.x, target.y + 10);
        renderContactInfo(dt);
        shapeRenderer.end();
    }

    @Override
    public String getTestExplication() {
        if (body1 == null || body2 == null)
            return null;
        return "Test Shape collision bounce between "
                + body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName()
                + " and " +
                body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
    }

    public InputProcessor addInputListener() {
        return new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                    changePosition(V2.tmp(unproject));
                }
                if (button == Input.Buttons.RIGHT) {
                    Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                    V2.set(target, unproject);
                    changeTarget();
                }
                drawContactInfo = false;
                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.F) {
                    changeType();
                    drawContactInfo = false;
                }
                if (keycode == Input.Keys.A) {
                    body1.active = true;
                    body2.active = true;
                }

                return false;
            }
        };
    }

}
