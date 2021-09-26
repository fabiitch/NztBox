package com.nzt.box.test.screens.w2d.physx.bouncing.triangle;

import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.shape.TriangleShape;
import com.nzt.box.test.screens.w2d.physx.bouncing.BaseSTOneBodyBouncing;
import com.nzt.gdx.math.shapes.Triangle;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.bouncing.triangle")
public class STBoucingTriangle extends BaseSTOneBodyBouncing {
    public STBoucingTriangle(FastTesterMain main) {
        super(main);
    }

    @Override
    protected BodyDef bodyDef() {
        return new BodyDef(BodyType.Dynamic).mass(1).restitution(1).receive(1).transfert(1);
    }

    @Override
    protected BodyShape bodyShape() {
        float[] vertices = new float[]{0, 0, 50, 50, 100,0};
        return new TriangleShape(new Triangle(vertices));
    }
}
