package com.nzt.box.test.api;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.world.World;
import com.nzt.gdx.test.api.fake.BaseGdxTest;
import org.junit.jupiter.api.BeforeEach;

public class BaseNztBoxTest extends BaseGdxTest {
    public World world;

    @BeforeEach
    public void init() {
        this.world = new World();
    }

    @Override
    public void renderTest(float dt) {
        world.step(dt);
    }

    protected Body createBall(int userData) {
        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 10);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        body.addFixture(fixture);
        body.userData = ""+userData;
        return body;
    }

}
