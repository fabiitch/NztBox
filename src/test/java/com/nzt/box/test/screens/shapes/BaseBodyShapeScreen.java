package com.nzt.box.test.screens.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.base.Box2dTestScreen;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.shapes")
public abstract class BaseBodyShapeScreen<B extends BodyShape> extends Box2dTestScreen {
    protected Body body;
    protected B bodyShape;

    public BaseBodyShapeScreen(FastTesterMain main) {
        super(main);
        body = new Body(BodyType.Dynamic);
        bodyShape = createBodyShape();
        Fixture fixture = new Fixture(bodyShape);
        body.addFixture(fixture);
        world.bodies.add(body);
        addListener();
    }

    protected abstract B createBodyShape();

    private void addListener() {
        SimpleClickInputHandler handler = new SimpleClickInputHandler() {
            @Override
            public boolean doTouchDown(int screenX, int screenY, int pointer, int button) {
                    Vector3 unproject = camera.unproject(new Vector3(screenX, screenX, 0));
                body.changePosition(unproject);
                return false;
            }

            @Override
            public boolean doTouchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }
        };
        Gdx.input.setInputProcessor(handler);
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
        renderShapeScreen(dt);

    }

}
