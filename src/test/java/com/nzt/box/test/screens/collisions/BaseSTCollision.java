package com.nzt.box.test.screens.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.*;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision")
public abstract class BaseSTCollision<S1 extends BodyShape, S2 extends BodyShape> extends Box2dTestScreen {
    Body body1, body2, bodyMove;
    private String shape1, shape2;

    protected Vector2 posBodyA = new Vector2(0, 0);
    protected Vector2 posBodyB = new Vector2(200, 0);

    public BaseSTCollision(FastTesterMain main) {
        super(main);
        addInputListener();

        body1 = new Body(BodyType.Dynamic);
        Fixture fixture1 = new Fixture(createBodyShape1());
        body1.addFixture(fixture1);
        world.bodies.add(body1);
        body1.setPosition(posBodyA);

        body2 = new Body(BodyType.Dynamic);
        Fixture fixture2 = new Fixture(createBodyShape2());
        body2.addFixture(fixture2);
        world.bodies.add(body2);
        body2.setPosition(posBodyB);
        debugMsg("Collision", false);

        ContactListener contactListener = new ContactListener() {
            @Override
            public void beginContact(ContactBody contactBody) {
                debugMsg("Collision", true);
                System.out.println("beginContact");
            }

            @Override
            public void endContact(ContactBody contactBody) {
                debugMsg("Collision", false);
                System.out.println("endContact");
            }

            @Override
            public void continusContact(ContactBody contactBody) {

            }
        };
        world.contactListener = contactListener;

        bodyMove = body1;
        shape1 = body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
        shape2 = body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();

        if (shape1.equals(shape2)) {
            shape1 += "A";
            shape2 += "B";
        }
        addInfoTestMsg();
        infoMsg("Press space for change body control");
        infoMsg("Press R for reset position");
    }


    protected abstract S1 createBodyShape1();

    protected abstract S2 createBodyShape2();


    @Override
    public String getExplication() {
        if (body1 == null || body2 == null)
            return null;
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
        Gdx.input.setInputProcessor(inputAdapter);
    }

    Vector2 tmp = new Vector2();

    @Override
    public void doRender(float dt) {
        debugMsg("Body " + shape2, body2.position);
        debugMsg("Body " + shape1, body1.position);

        debugMsg("Shape" + shape2, body2.fixtures.get(0).bodyShape.getPosition(tmp), HudDebugPosition.BOT_RIGHT);
        debugMsg("Shape" + shape1, body1.fixtures.get(0).bodyShape.getPosition(tmp), HudDebugPosition.BOT_RIGHT);
    }


    protected CircleShape createCircle(float radius) {
        return new CircleShape(new Circle(0, 0, radius));
    }

    protected PolygonShape createPolygon1() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        return new PolygonShape(new Polygon(vertices));
    }

    protected RectangleShape createRectangle(float witdh, float height) {
        Rectangle rectangle = new Rectangle(0, 0, witdh, height);
        return new RectangleShape(rectangle);
    }
}
