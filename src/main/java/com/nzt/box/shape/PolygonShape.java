package com.nzt.box.shape;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;
import com.nzt.gdx.math.shapes.builders.PolygonBuilder;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;

/**
 * Only convex polygons
 */
public class PolygonShape extends BodyShape<Polygon> {

    public PolygonShape(Polygon shape) {
        super(shape);
        PolygonUtils.ensureClockWise(this.shape);
        this.boundingRect = shape.getBoundingRectangle();
        if (!PolygonUtils.isConvex(shape))
            throw new GdxRuntimeException("NztBox, PolygonShape allow only convex polygons");
    }

    @Override
    public Rectangle computeBoundingRect() {
        return this.shape.getBoundingRectangle();
    }

    public PolygonShape(float[] vertices) {
        super(new Polygon(vertices));
    }

    public PolygonShape(Vector2[] vertices) {
        super(PolygonBuilder.polygon(vertices));
    }

    @Override
    public Vector2 getPosition(Vector2 pos) {
        return pos.set(shape.getX(), shape.getY());
    }

    @Override
    public void draw(NzShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(shape);
    }

    @Override
    public void setRotation(float rotation) {
        shape.setRotation(rotation);
    }

    @Override
    public void rotate(float amount) {
        shape.rotate(amount);
    }

    @Override
    public void scale(float amount) {
        shape.scale(amount);
    }

    @Override
    public void setPosition(float x, float y) {
        shape.setPosition(x, y);
    }

    @Override
    public ShapeContact getContactVisitor(ContactResolver resolver) {
        return resolver.get(this);
    }

    @Override
    public boolean testContact(ShapeContact visitor) {
        return visitor.testContact(shape);
    }

    @Override
    public void replace(ShapeContact visitor, Body bodyToReplace) {
        visitor.replace(bodyToReplace, shape);
    }

    @Override
    public void calculCollisionData(ShapeContact visitor, ContactFixture contactFixture) {
        visitor.calculCollisionData(shape, contactFixture);
    }
}
