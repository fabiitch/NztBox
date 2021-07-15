package com.nzt.box.shape;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.shape.contact.ShapeContactVisitor;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public abstract class BodyShape<S extends Shape2D> {
    public S shape;

    public BodyShape(S shape) {
        this.shape = shape;
    }

    public abstract void draw(NzShapeRenderer shapeRenderer);

    public abstract void rotate(float amount);

    public abstract void scale(float amount);

    public abstract void changeBodyPosition(Vector2 position);

    public abstract void changeBodyPosition(float x, float y);

    public abstract ShapeContactVisitor getContactVisitor();

    public abstract void testContact(ShapeContactVisitor visitor);
}
