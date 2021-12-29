package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.badlogic.gdx.math.MathUtils;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.TriangleShape;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryReplaceMassRandomTriangles extends BaseSTryReplaceMassBodies {
    public STryReplaceMassRandomTriangles(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        float min = 20;
        float max = 100;
        Triangle triangle = new Triangle(MathUtils.random(min, max), MathUtils.random(min, max),
                MathUtils.random(min, max), MathUtils.random(min, max),
                MathUtils.random(min, max), MathUtils.random(min, max));
        return new TriangleShape(triangle);
    }
}
