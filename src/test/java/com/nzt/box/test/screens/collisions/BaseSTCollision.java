package com.nzt.box.test.screens.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public abstract class BaseSTCollision extends Box2dTestScreen {
    Body body1, body2, bodyMove;
    String shape1, shape2;

    public BaseSTCollision(FastTesterMain main) {
        super(main);
        addInputListener();

        body1 = new Body(BodyType.Dynamic);
        Fixture fixture1 = new Fixture(createBodyShape1());
        body1.addFixture(fixture1);
        world.bodies.add(body1);
        body1.setPosition(new Vector2(0, 0));

        body2 = new Body(BodyType.Dynamic);
        Fixture fixture2 = new Fixture(createBodyShape2());
        body2.addFixture(fixture2);
        world.bodies.add(body2);
        body2.setPosition(new Vector2(150, 0));
        debugMsg("Collision", false);
        infoMsg("Press space for change body control");
        ContactListener contactListener = new ContactListener() {
            @Override
            public void beginContact(ContactBody contactBody) {
                debugMsg("Collision", true);
            }

            @Override
            public void endContact(ContactBody contactBody) {
                debugMsg("Collision", false);
            }

            @Override
            public void continusContact(ContactBody contactBody) {

            }
        };
        world.contactListener = contactListener;

        bodyMove = body1;
        shape1 = body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
        shape2 = body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();

        if(shape1.equals(shape2)){
            shape1+="A";
            shape2+="B";
        }
    }


    protected abstract BodyShape createBodyShape1();

    protected abstract BodyShape createBodyShape2();


    @Override
    public String getExplication() {
        return "Test Collision between "
                + body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName()
                + " and " +
                body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
    }


    private void addInputListener() {
        InputAdapter inputAdapter = new InputAdapter() {

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
                if (keycode == Input.Keys.Z || keycode == Input.Keys.W || keycode == Input.Keys.UP) {
                    y += velocity;
                } else if (keycode == Input.Keys.A || keycode == Input.Keys.Q || keycode == Input.Keys.LEFT) {
                    x -= velocity;
                } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
                    y -= velocity;
                } else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
                    x += velocity;
                }
                if (keycode == Input.Keys.SPACE) {
                    if (bodyMove == body1)
                        bodyMove = body2;
                    else
                        bodyMove = body1;
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
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void doRender(float dt) {
        debugMsg(shape1, body1.position);
        debugMsg(shape2, body2.position);
    }


}
