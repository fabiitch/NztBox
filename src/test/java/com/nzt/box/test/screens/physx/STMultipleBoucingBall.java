package com.nzt.box.test.screens.physx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;
import com.nzt.gdx.utils.GdxUtils;

@TestScreenList(group = "2D.physx")
public class STMultipleBoucingBall extends Box2dTestScreen {

    BitmapFont bitmapFont;

    public STMultipleBoucingBall(FastTesterMain main) {
        super(main);
        bitmapFont = new BitmapFont();

        debugRenderer.debugSettings.drawVelocity = false;
        createWallAroundScreen();
        rectScreen = GdxUtils.screenAsRectangle(rectScreen,true);
        for (int i = 0; i < 100; i++)
            createBall(i);
    }

    private Body createBall(int userData) {
        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture<CircleShape> fixture = new Fixture<CircleShape>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        body.userData = userData;
        Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(250);
        body.setVelocity(velocity);
        return body ;
    }

    Vector2 tmpPos = new Vector2();
    Rectangle rectScreen = new Rectangle();

    @Override
    public void doRender(float dt) {
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.PURPLE);
        shapeRenderer.rect(rectScreen);
        shapeRenderer.end();
        Array<Body> bodiesNew = new Array<>();
        Array<Body> bodies = world.data.bodies;
        for (int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            body.getPosition(tmpPos);
            if (rectScreen.contains(tmpPos)) {
                bodiesNew.add(body);
            }
        }
        bodiesNew.shrink();
        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "Bodies :" + bodiesNew.size, 50, 50);
        bitmapFont.draw(spriteBatch, "BodiesW :" + bodies.size, 50, 200);
        spriteBatch.end();
        bodiesNew.clear();
    }

    @Override
    public String getTestExplication() {
        return "Multiple circle collion";
    }

    @Override
    public void disposeTestScreen() {
        super.disposeTestScreen();
        bitmapFont.dispose();
    }
}
