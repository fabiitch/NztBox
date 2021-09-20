package com.nzt.box.test.screens.w2d.collisions.replace.mass;

import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.TriangleShape;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STReplaceMassTriangles extends BaseSTReplaceMassBodies {
    public STReplaceMassTriangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 30, 30, 60, 0};
        return new TriangleShape(new Triangle(vertices));
    }
}
