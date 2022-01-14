package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryReplaceMassPolygons extends BaseSTryReplaceMassBodies {
    public STryReplaceMassPolygons(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 25, 25, 50, 0, 0, -25};
        return new PolygonShape(new Polygon(vertices));
    }
}
