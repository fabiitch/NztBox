package com.nzt.box.test.screens.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STSimpleCollision extends Box2dTestScreen {
    Body body1, body2;

    boolean collision = false;

    public STSimpleCollision(FastTesterMain main) {
        super(main);
        addListener();
                body1 = new Body(BodyType.Dynamic);
        CircleShape circleShape1 = new CircleShape(new Circle(0, 0, 50));
        Fixture fixture1 = new Fixture(circleShape1);
        body1.addFixture(fixture1);
        world.bodies.add(body1);

        body2 = new Body(BodyType.Dynamic);
        CircleShape circleShape2 = new CircleShape(new Circle(100, 100, 50));
        Fixture fixture2 = new Fixture(circleShape2);
        body2.addFixture(fixture2);
        world.bodies.add(body2);
    }

    private void addListener() {
        InputAdapter inputAdapter = new InputAdapter() {

            public int x = 0, y = 0;
            final int velocity = 25;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 unproject = camera.unproject(new Vector3(screenX, screenY, 0));
                body1.changePosition(unproject);
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
                body1.setVelocity(x, y);
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
                body1.setVelocity(x, y);
                debugMsg("Velocity", x + "/" + y);
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("Collision", collision);
    }

    @Override
    public String getExplication() {
        return "Simple collision between two Circle";
    }
}
