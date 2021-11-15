package com.nzt.box.shape;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.detector.ShapeContact;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public abstract class BodyShape<S extends Shape2D> {
    public S shape;
    public Rectangle boundingRect;

    public BodyShape(S shape) {
        this.shape = shape;
    }

    public abstract Rectangle computeBoundingRect();

    public abstract Vector2 getPosition(Vector2 pos);

    public final void setPosition(Vector2 position) {
        setPosition(position.x, position.y);
    }

    public abstract void setPosition(float x, float y);

    public abstract void draw(NzShapeRenderer shapeRenderer);

    public abstract void setRotation(float rotation);

    public abstract void rotate(float amount);

    public abstract void scale(float scale);


    public abstract ShapeContact getContactVisitor();

    public abstract boolean testContact(ShapeContact visitor);

    public abstract void replace(ShapeContact visitor, ContactFixture contactFixture);

    public abstract void calculCollisionData(ShapeContact visitor, ContactFixture contactFixture);


}
