package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.TriangleShape;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryReplaceMassTriangles extends BaseSTryReplaceMassBodies {
    public STryReplaceMassTriangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 30, 30, 60, 0};
        return new TriangleShape(new Triangle(vertices));
    }
}
