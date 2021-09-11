package com.nzt.box.shape;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class CircleShape extends BodyShape<Circle> {

    public CircleShape(Circle shape) {
        super(shape);
    }

    public CircleShape(float radius) {
        this(new Circle(0, 0, radius));
    }

    @Override
    public float calculMaxDst() {
        return shape.radius;
    }

    @Override
    public Vector2 getPosition(Vector2 pos) {
        return pos.set(shape.x, shape.y);
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

    @Override
    public void replace(ShapeContact visitor, ContactFixture contactFixture) {
        visitor.replace(shape, contactFixture);
    }

    @Override
    public void calculNormal(ShapeContact visitor, ContactFixture contactFixture) {
        visitor.calculNormal(shape, contactFixture);
    }
}
