package com.nzt.box.test.screens.collisions;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCollisionCirclePolygon extends BaseSTCollision {
    public STCollisionCirclePolygon(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape createBodyShape1() {
        return new CircleShape(new Circle(0, 0, 50));
    }

    @Override
    protected BodyShape createBodyShape2() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        return new PolygonShape(new Polygon(vertices));
    }
}
