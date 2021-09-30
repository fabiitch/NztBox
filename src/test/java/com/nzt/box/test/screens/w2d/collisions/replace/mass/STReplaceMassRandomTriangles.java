package com.nzt.box.test.screens.w2d.collisions.replace.mass;

import com.badlogic.gdx.math.MathUtils;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.TriangleShape;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STReplaceMassRandomTriangles extends BaseSTReplaceMassBodies {
    public STReplaceMassRandomTriangles(FastTesterMain main) {
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
