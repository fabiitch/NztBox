package com.nzt.box.test.screens.shapes;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STPolygonBodyShape extends BaseBodyShapeScreen<PolygonShape>{
    public STPolygonBodyShape(FastTesterMain main) {
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
    public String getExplication() {
        return "Test PolygonBodyShape";
    }
}
