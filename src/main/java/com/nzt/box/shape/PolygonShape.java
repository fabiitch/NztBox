package com.nzt.box.shape;

import com.badlogic.gdx.math.Polygon;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class PolygonShape extends BodyShape<Polygon> {

    public PolygonShape(Polygon shape) {
        super(shape);
    }

    @Override
    public void draw(NzShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(shape);
    }

    @Override
    public void rotate(float amount) {

    }

    @Override
    public void scale(float amount) {

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
    public void replace(ShapeContact visitor, ContactBody contactBody) {

        visitor.replace(shape, contactBody);
    }
}
