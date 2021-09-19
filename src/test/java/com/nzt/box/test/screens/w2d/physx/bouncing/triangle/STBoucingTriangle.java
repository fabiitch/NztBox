package com.nzt.box.test.screens.w2d.physx.bouncing.triangle;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.shape.TriangleShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTOneBodyBouncing;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBoucingTriangle extends BaseSTOneBodyBouncing {
    public STBoucingTriangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyDef bodyDef() {
        BodyDef bodyDef = new BodyDef(BodyType.Dynamic);
        bodyDef.mass = 1;
        bodyDef.restitution = 1;
        bodyDef.transfert = 1;
        return bodyDef;
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 50, 50, 100,0};
        return new TriangleShape(new Triangle(vertices));
    }
}
