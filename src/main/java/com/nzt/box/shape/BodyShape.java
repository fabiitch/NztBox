package com.nzt.box.shape;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.contact.ContactBody;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public abstract class BodyShape<S extends Shape2D> {
    public S shape;

    public float maxDst;

    public BodyShape(S shape) {
        this.shape = shape;
        maxDst = calculMaxDst();
    }

    public abstract float calculMaxDst();

    public abstract Vector2 getPosition(Vector2 pos);

    public abstract void draw(NzShapeRenderer shapeRenderer);

    public abstract void rotate(float amount);

    public abstract void scale(float scale);

    public final void changeBodyPosition(Vector2 position) {
        changeBodyPosition(position.x, position.y);
    }

    public abstract void changeBodyPosition(float x, float y);

    public abstract ShapeContact getContactVisitor();

    public abstract boolean testContact(ShapeContact visitor);

    public abstract void replace(ShapeContact visitor, ContactBody contactBody);
}
