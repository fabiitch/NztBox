package com.nzt.box.test.screens.shapes;

import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.shape.RectangleShape;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STRectangleBodyShape extends BaseBodyShapeScreen<RectangleShape> {
    public STRectangleBodyShape(FastTesterMain main) {
        super(main);
    }

    @Override
    protected RectangleShape createBodyShape() {
        Rectangle rect = new Rectangle(0, 0, 100, 50);
        return new RectangleShape(rect);
    }

    @Override
    public void renderShapeScreen(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Simple rectangle shape on body";
    }
}
