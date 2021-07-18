package com.nzt.box.shape;

import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

/**
 * no rotation
 */
public class RectangleShape extends BodyShape<Rectangle> {

    public RectangleShape(Rectangle shape) {
        super(shape);
    }

    @Override
    public void draw(NzShapeRenderer shapeRenderer) {
        shapeRenderer.rect(shape);
    }

    @Override
    public void rotate(float amount) {

    }

    @Override
    public void scale(float scale) {
        if (scale < 0) {
            shape.width /= scale;
            shape.height /= scale;
        }
        shape.width *= scale;
        shape.height *= scale;
    }

    @Override
    public void changeBodyPosition(float x, float y) {

    }

    @Override
    public ShapeContact getContactVisitor() {
        return null;
    }

    @Override
    public boolean testContact(ShapeContact visitor) {
        return false;
    }

    @Override
    public void replace(ShapeContact visitor, ContactBody contactBody) {
    }
}
