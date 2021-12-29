package com.nzt.box.test.s_try.w2d.shapes;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryPolygonBodyShape extends BaseSTryBodyShape<PolygonShape> {
    public STryPolygonBodyShape(FastTesterMain main) {
        super(main);
    }

    @Override
    protected PolygonShape createBodyShape() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        return new PolygonShape(new Polygon(vertices));
    }

    @Override
    public void renderShapeScreen(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Test PolygonBodyShape";
    }
}
