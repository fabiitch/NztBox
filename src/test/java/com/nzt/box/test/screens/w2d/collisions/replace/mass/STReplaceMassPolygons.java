package com.nzt.box.test.screens.w2d.collisions.replace.mass;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STReplaceMassPolygons extends BaseSTReplaceMassBodies {
    public STReplaceMassPolygons(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 25, 25, 50, 0, 0, -25};
        return new PolygonShape(new Polygon(vertices));
    }
}
