package com.nzt.box.test.s_try.w2d.shapes;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.mains.FastTesterMain;

public class STryCirleBodyShape extends BaseSTryBodyShape<CircleShape> {

    public STryCirleBodyShape(FastTesterMain main) {
        super(main);
    }

    @Override
    protected CircleShape createBodyShape() {
        Circle circle = new Circle(0, 0, 150);
        return new CircleShape(circle);
    }

    @Override
    public void renderShapeScreen(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Simple Circle BodyShape test";
    }
}
