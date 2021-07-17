package com.nzt.box.shape;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.contact.detector.ContactResolver;
import com.nzt.box.shape.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class CircleShape extends BodyShape<Circle> {

    public CircleShape(Circle shape) {
        super(shape);
    }

    @Override
    public void draw(NzShapeRenderer shapeRenderer) {
        shapeRenderer.circle(shape);
    }

    @Override
    public void rotate(float amount) {

    }

    @Override
    public void scale(float scale) {
        if (scale < 0)
            shape.radius /= scale;
        shape.radius *= scale;
    }

    @Override
    public void changeBodyPosition(Vector2 position) {
        shape.setPosition(position.x, position.y);
    }

    @Override
    public void changeBodyPosition(float x, float y) {
        shape.setPosition(x, y);
    }

    @Override
    public ShapeContact getContactVisitor() {
        return ContactResolver.get(this);
    }

    @Override
    public boolean testContact(ShapeContact visitor) {
       return visitor.testContact(shape);
    }
}
