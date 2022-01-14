package com.nzt.box.test.runnable;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.s_try.utils.BoxSTHelp;
import com.nzt.box.world.World;
import com.nzt.gdx.test.st.tester.UnitTestScreen;
import com.nzt.gdx.mains.FastTesterMain;
import org.junit.jupiter.api.BeforeEach;

public class BaseRunnableBoxTest extends UnitTestScreen {
    public World world;
    public int userDataCount = 0;
    public BodyDef basicBodyDef = new BodyDef();
    public BoxSTHelp boxSTHelp;

    public BaseRunnableBoxTest(FastTesterMain main) {
        super(main);
    }

    @Override
    public void renderUnitTest(float dt) {
        world.step(dt);
    }

    @Override
    public String getTestExplication() {
        return null;
    }

    @Override
    public void renderTestScreen(float dt) {
        world.step(dt);
    }

    @Override
    public void disposeTestScreen() {

    }

    @BeforeEach
    public void initNztBox() {
        this.world = new World();
        this.boxSTHelp = new BoxSTHelp(world);
        this.userDataCount = 0;
    }

    protected Body createBall(BodyDef bodyDef) {
        Body body = new Body(bodyDef);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        fixture.userData = "F" + userDataCount++;
        body.addFixture(fixture);
        body.userData = "B" + userDataCount++;
        world.addBody(body);
        return body;
    }

    protected Body createBall(BodyType bodyType) {
        basicBodyDef.bodyType = bodyType;
        Body body = createBall(basicBodyDef);
        basicBodyDef.bodyType = BodyType.Static;
        return body;
    }

    protected Body createBall() {
        return createBall(basicBodyDef);
    }


    public Vector2 v(float x, float y) {
        return new Vector2(x, y);
    }

    public Vector3 v(float x, float y, float z) {
        return new Vector3(x, y, z);
    }

}
