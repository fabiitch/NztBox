package com.nzt.box.test.api.run;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.world.World;
import com.nzt.gdx.test.api.tester.BaseGdxTest;
import org.junit.jupiter.api.BeforeEach;

public class BaseNztBoxTest extends BaseGdxTest {
    public World world;
    public int userDataCount = 0;
    public BodyDef basicBodyDef = new BodyDef();

    @BeforeEach
    public void initNztBox(){
        this.world = new World();
        this.userDataCount = 0;
    }

    @Override
    public void renderTest(float dt) {
        world.step(dt);
    }

    protected Body createBall(BodyDef bodyDef) {
        Body body = new Body(bodyDef);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        body.userData = "" + userDataCount++;
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
}
