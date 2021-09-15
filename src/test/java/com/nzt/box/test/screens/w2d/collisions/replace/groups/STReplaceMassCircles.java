package com.nzt.box.test.screens.w2d.collisions.replace.groups;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.replace.groups")
public class STReplaceMassCircles extends Box2dTestScreen {
    public STReplaceMassCircles(FastTesterMain main) {
        super(main);
        for (int i = 0; i < 150; i++) {
            createBall(i);
        }
    }

    @Override
    public void doRender(float dt) {
        world.data.bodies.forEach(b -> b.dirty = true);
    }

    private Body createBall(int userData) {
        Body body = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 25);
        CircleShape shape = new CircleShape(circle);
        Fixture<CircleShape> fixture = new Fixture<CircleShape>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        fixture.userData = "" + userData;
        body.userData = "" + userData;
        return body;
    }

    @Override
    public String getTestExplication() {
        return "Test de replace plein de cercle";
    }
}
