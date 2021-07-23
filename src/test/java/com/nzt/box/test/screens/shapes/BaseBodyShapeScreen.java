package com.nzt.box.test.screens.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.BodyShape")
public abstract class BaseBodyShapeScreen<B extends BodyShape> extends Box2dTestScreen {
    protected Body body;
    protected B bodyShape;
    protected Vector2 tmp = new Vector2();

    public BaseBodyShapeScreen(FastTesterMain main) {
        super(main);
        body = new Body(BodyType.Dynamic);
        bodyShape = createBodyShape();
        Fixture fixture = new Fixture(bodyShape);
        body.addFixture(fixture);
        world.addBody(body);
        addListener();
        infoMsg("Click for change position");
        infoMsg("Z/Q/S/D for move");
        infoMsg("Space for reset");
        infoMsg("R for rotation");
        infoMsg("T for Scale");
    }

    protected abstract B createBodyShape();

    private void addListener() {
        InputAdapter inputAdapter = new InputAdapter() {

            public int x = 0, y = 0;
            final int velocity = 25;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                body.setPosition(unproject);
                return false;
            }

            @Override
            public boolean keyDown(int keycode) {

                if (keycode == Input.Keys.SPACE) {
                    bodyShape = createBodyShape();
                    body.setPosition(new Vector2(0, 0));
                    return false;
                }
                if (keycode == Input.Keys.R) {
                    doRotate = !doRotate;
                }
                if (keycode == Input.Keys.T) {
                    doScale = !doScale;
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
                body.setVelocity(x, y);
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
                body.setVelocity(x, y);
                debugMsg("Velocity", x + "/" + y);
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputAdapter);
    }

    boolean doScale = false;
    final float scaleAmount = 0.05f;
    float max = 2;
    float min = 0.1f;
    float scale = 1;
    boolean grow = true;

    float rotateAmount = 0.5f;
    boolean doRotate = true;

    public abstract void renderShapeScreen(float dt);

    @Override
    public void doRender(float dt) {
        if (doScale) {
            if (grow) {
                scale += scaleAmount;
                bodyShape.scale(1.1f);
                if (scale > max)
                    grow = false;
            } else {
                scale -= scaleAmount;
                bodyShape.scale(0.9f);
                if (scale < min)
                    grow = true;
            }
        }
        if (doRotate)
            bodyShape.rotate(rotateAmount);
        debugMsg("Scale", scaleAmount);
        debugMsg("Rotation", rotateAmount);
        debugMsg("Position", bodyShape.getPosition(tmp));
        renderShapeScreen(dt);

    }

}
