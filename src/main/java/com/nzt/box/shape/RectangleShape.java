package com.nzt.box.shape;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ContactResolver;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;

/**
 * no rotation
 */
public class RectangleShape extends BodyShape<Rectangle> {

    public RectangleShape(Rectangle shape) {
        super(shape);
    }

    @Override
    public float calculMaxDst() {
        return RectangleUtils.dstVertexCenter(shape);
    }

    public RectangleShape(float witdh, float height) {
        super(new Rectangle(0, 0, witdh, height));
    }


    @Override
    public Vector2 getPosition(Vector2 pos) {
        return pos.set(shape.x, shape.y);
    }

    @Override
    public void changeBodyPosition(float x, float y) {
        shape.setPosition(x - shape.width / 2, y - shape.height / 2);
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
