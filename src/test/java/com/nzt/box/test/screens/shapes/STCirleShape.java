package com.nzt.box.test.screens.shapes;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.shape.CircleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STCirleShape extends BaseBodyShapeScreen<CircleShape> {

    public STCirleShape(FastTesterMain main) {
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
    public String getExplication() {
        return "Simple Circle BodyShape test";
    }
}
