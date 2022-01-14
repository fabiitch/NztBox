package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.*;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.collision.replace.mass")
public class STryReplaceAllShapes extends BaseBox2DSTry {
    int userData = 1;
    BodyDef bodyDef;

    public STryReplaceAllShapes(FastTesterMain main) {
        super(main);
        bodyDef = new BodyDef(BodyType.Dynamic);
        bodyDef.mass = 1;
        bodyDef.restitution = 1;
        bodyDef.transfert = 1;
        for (int i = 0; i < 150; i++) {
            createBody();
        }
    }

    int count = 1;

    private Body createBody() {
        BodyShape bodyShape = null;
        if (count == 1) {
            bodyShape = new CircleShape(25);
        } else if (count == 2) {
            float[] vertices = new float[]{0, 0, 25, 25, 50, 0, 0, -25};
            bodyShape = new PolygonShape(new Polygon(vertices));
        } else if (count == 3) {
            bodyShape = new RectangleShape(50, 50);
        } else {
            float[] vertices = new float[]{0, 0, 30, 30, 60, 0};
            bodyShape = new TriangleShape(new Triangle(vertices));
        }
        count++;
        if (count > 4)
            count = 1;

        Body body = new Body(bodyDef);
        BodyShape shape = bodyShape;
        Fixture<?> fixture = new Fixture<>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        body.userData = userData++;
        return body;
    }

    @Override
    public void doRender(float dt) {
        world.data.bodies.forEach(b -> b.dirtyPos = true);
    }

    @Override
    public String getTestExplication() {
        return "Test mass shape";
    }
}
