package com.nzt.box.shape;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;
import com.nzt.gdx.math.shapes.utils.PolygonUtils;

/**
 * Only convex polygons
 */
public class PolygonShape extends BodyShape<Polygon> {

    public PolygonShape(Polygon shape) {
        super(shape);
        boolean convex = PolygonUtils.isConvex(shape);
        if(!convex)
            throw new GdxRuntimeException("NztBox, PolygonShape allow only convex polygons");
    }

    @Override
    public float calculMinDst() {
        return 0;
    }

    @Override
    public float calculMaxDst() {
        return PolygonUtils.getMaxDstVertex(shape);
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
    public void rotate(float amount) {
        shape.rotate(amount);
    }

    @Override
    public void scale(float amount) {
        shape.scale(amount);
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
