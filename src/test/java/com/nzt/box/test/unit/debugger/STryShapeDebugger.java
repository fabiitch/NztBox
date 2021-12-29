package com.nzt.box.test.unit.debugger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.*;
import com.nzt.box.test.s_try.utils.BoxCamera2DController;
import com.nzt.gdx.math.shapes.Segment;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;
import com.nzt.gdx.test.utils.archi.screens.ScreenTry;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "debug")
public class STryShapeDebugger extends ScreenTry {

    private Shape2D shape1;
    private Shape2D shape2;
    private OrthographicCamera camera;
    private BoxCamera2DController camera2DMover;

    public STryShapeDebugger(FastTesterMain main) {
        super(main);
        this.camera = new OrthographicCamera(1080, 720);
        camera2DMover = new BoxCamera2DController(camera);
        Gdx.input.setInputProcessor(camera2DMover);

        infoMsg("Red", "shape1");
        infoMsg("Blue", "shape1");
        shape1 = ShapeFromString.rectangle("[50.0,55.545,20.0,10.0]");
        shape2 = ShapeFromString.polygon("[-12.867004, -288.01547,37.132996, -238.01547, 87.132996, -288.01547,-12.867004, -338.01547]");
        debugMsg("pos shape1", getPosition(shape1));
        debugMsg("pos shape2", getPosition(shape2));
    }

    @Override
    public String getTestExplication() {
        return "Use it for debug ur shape";
    }

    @Override
    public void renderTestScreen(float dt) {
        camera2DMover.updateCameraPosition();
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.RED);
        drawShape(shape1);
        shapeRenderer.setColor(Color.BLUE);
        drawShape(shape2);
        shapeRenderer.end();
        debugMsg("pos shape1", getPosition(shape1));
        debugMsg("pos shape2", getPosition(shape2));
    }

    @Override
    public void disposeTestScreen() {

    }

    Vector2 tmp = new Vector2();

    private Vector2 getPosition(Shape2D shape2D) {
        if (shape2D instanceof Rectangle) {
            return ((Rectangle) shape2D).getPosition(tmp);
        } else if (shape2D instanceof Circle) {
            Circle c = (Circle) shape2D;
            return tmp.set(c.x, c.y);
        } else if (shape2D instanceof Polygon) {
            Polygon p = (Polygon) shape2D;
            return tmp.set(p.getX(), p.getY());
        } else if (shape2D instanceof Segment) {
            Segment p = (Segment) shape2D;
            return p.a;
        }
        return tmp;
    }

    private void drawShape(Shape2D shape2D) {
        if (shape2D instanceof Rectangle) {
            this.shapeRenderer.rect((Rectangle) shape2D);
        } else if (shape2D instanceof Circle) {
            this.shapeRenderer.circle((Circle) shape2D);
        } else if (shape2D instanceof Polygon) {
            this.shapeRenderer.polygon((Polygon) shape2D);
        } else if (shape2D instanceof Segment) {
            this.shapeRenderer.segment((Segment) shape2D);
        }
    }
}
