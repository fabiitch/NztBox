package com.nzt.box.shape;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.contact.ContactResolver;
import com.nzt.box.shape.contact.ShapeContactVisitor;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class PolygonShape extends BodyShape<Polygon> {

    public PolygonShape(Polygon shape) {
        super(shape);
    }

    @Override
    public void draw(NzShapeRenderer shapeRenderer) {

    }

    @Override
    public void rotate(float amount) {

    }

    @Override
    public void scale(float amount) {

    }

    @Override
    public void changeBodyPosition(Vector2 position) {

    }

    @Override
    public void changeBodyPosition(float x, float y) {

    }

    @Override
    public ShapeContactVisitor getContactVisitor() {
        return ContactResolver.get(this);
    }

    @Override
    public void testContact(ShapeContactVisitor visitor) {
        visitor.testContact(shape);
    }
}
