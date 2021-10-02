package com.nzt.box.test.screens.w2d.collisions.multiple.mass;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.screens.w2d.BaseSTMultiplePositionsBody;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.collision.replace.mass")
public abstract class BaseSTReplaceMassBodies extends BaseSTMultiplePositionsBody {
    int userData = 1;
    BodyDef bodyDef;

    public BaseSTReplaceMassBodies(FastTesterMain main) {
        super(main);
        bodyDef = new BodyDef(BodyType.Dynamic);
        bodyDef.mass = 1;
        bodyDef.restitution = 1;
        bodyDef.transfert = 1;

        for (int i = 0; i < 150; i++) {
            createBody();
        }
    }

    private Body createBody() {
        Body body = new Body(bodyDef);
        BodyShape shape = bodyShape();
        Fixture<?> fixture = new Fixture<>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        body.userData = userData++;
        return body;
    }


    protected abstract BodyShape bodyShape();

    @Override
    public void doRenderM(float dt) {
        world.data.bodies.forEach(b -> b.dirty = true);
    }

    @Override
    public String getTestExplication() {
        return "Test mass shape";
    }
}
