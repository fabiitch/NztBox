package com.nzt.box.test.screens.w2d.collisions.twobody.detection;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.screens.w2d.BaseST2Body;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.detection")
abstract class BaseSTCollisionDetection<S1 extends BodyShape, S2 extends BodyShape> extends BaseST2Body<S1, S2> {

    public BaseSTCollisionDetection(FastTesterMain main) {
        super(main, BodyType.Kinematic, BodyType.Kinematic);
        infoMsg("Press F for change body control");
        infoMsg("Press R for reset position");

        infoMsg("B => rotate body1");
        infoMsg("N => rotate body2");
        world.contactListener = new ContactListener() {
            @Override
            public void beginContact(ContactFixture contactBody) {
                HudDebug.update("Collision", true, Color.RED);

            }

            @Override
            public void endContact(ContactFixture contactBody) {
                HudDebug.update("Collision", false, Color.BLUE);
            }

            @Override
            public void continueContact(ContactFixture contactBody) {

            }

            @Override
            public void preSolve(ContactFixture contactBody) {
                contactBody.calculCollisionData = true;
            }
        };
        debugRenderer.debugSettings.drawContactNormal = true;
    }

    @Override
    public String getTestExplication() {
        if (body1 == null || body2 == null)
            return null;
        return "Test Shape collision Detection between "
                + body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName()
                + " and " +
                body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
    }

    public InputProcessor addInputListener() {
        return new InputAdapter() {

            public int x = 0, y = 0;
            final int velocity = 25;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                bodyMove.setPosition(unproject);
                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.B) {
                    body1.angularVelocity = body1.angularVelocity == 50 ? 0 : 50;
                }
                if (keycode == Input.Keys.N) {
                    body2.angularVelocity = body2.angularVelocity == 50 ? 0 : 50;
                }


                if (keycode == Input.Keys.Z || keycode == Input.Keys.W || keycode == Input.Keys.UP) {
                    y += velocity;
                } else if (keycode == Input.Keys.A || keycode == Input.Keys.Q || keycode == Input.Keys.LEFT) {
                    x -= velocity;
                } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
                    y -= velocity;
                } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
                    x += velocity;
                }
                if (keycode == Input.Keys.F) {
                    if (bodyMove == body1)
                        bodyMove = body2;
                    else
                        bodyMove = body1;
                }
                if (keycode == Input.Keys.R) {
                    body1.setPosition(posBodyA);
                    body2.setPosition(posBodyB);
                }
                bodyMove.setVelocity(x, y);
                debugMsg("Velocity", x + "/" + y);
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.Z || keycode == Input.Keys.W || keycode == Input.Keys.UP) {
                    y -= velocity;
                } else if (keycode == Input.Keys.A || keycode == Input.Keys.Q || keycode == Input.Keys.LEFT) {
                    x += velocity;
                } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
                    y += velocity;
                } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
                    x -= velocity;
                }
                bodyMove.setVelocity(x, y);
                debugMsg("Velocity", x + "/" + y);
                return false;
            }
        };
    }

}
