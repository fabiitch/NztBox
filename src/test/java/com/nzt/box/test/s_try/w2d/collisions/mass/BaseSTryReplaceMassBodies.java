package com.nzt.box.test.s_try.w2d.collisions.mass;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.s_try.w2d.BaseSTryMultipleBody;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.collision.replace.mass")
abstract class BaseSTryReplaceMassBodies extends BaseSTryMultipleBody {
    int userData = 1;
    BodyDef bodyDef;

    public BaseSTryReplaceMassBodies(FastTesterMain main) {
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
        world.data.bodies.forEach(b -> b.dirtyPos = true);
    }

    @Override
    public String getTestExplication() {
        return "Test mass shape";
    }
}
