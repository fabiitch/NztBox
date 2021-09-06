package com.nzt.box.test.screens.w2d.shapes;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCirleBodyShape extends BaseBodyShapeScreen<CircleShape> {

    public STCirleBodyShape(FastTesterMain main) {
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
