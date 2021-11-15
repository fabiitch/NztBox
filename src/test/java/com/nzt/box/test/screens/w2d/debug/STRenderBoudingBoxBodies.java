package com.nzt.box.test.screens.w2d.debug;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.debug.BoxDebugSettings;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.screens.w2d.BaseSTMultipleBody;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.debug")
public class STRenderBoudingBoxBodies extends BaseSTMultipleBody {
    public STRenderBoudingBoxBodies(FastTesterMain main) {
        super(main);
        createWallAroundScreen();
        BoxDebugSettings debugSettings = debugRenderer.debugSettings;
        debugSettings.drawBoudingsBoxBodies = true;
        debugSettings.drawBoudingsBoxFixtures = true;
        debugSettings.drawCenter = true;
        debugSettings.drawContactPoint = false;
        debugSettings.drawBodyUserData = false;
        debugSettings.drawInactive = false;
        debugSettings.drawVelocity = false;
        debugSettings.drawQuadTree = false;

        Body body = new Body(BodyType.Dynamic);
        Fixture fixtureA = new Fixture(new CircleShape(10));
        body.addFixture(fixtureA);
        Fixture fixtureB = new Fixture(new CircleShape(20));
        body.addFixture(fixtureB);
        Fixture fixtureC = new Fixture(new RectangleShape(100, 10));
        body.addFixture(fixtureC);


        body.setVelocity(new Vector2(1, 0).setToRandomDirection().setLength(150));

        world.addBody(body);
    }

    @Override
    public void doRenderM(float dt) {

    }

    @Override
    public String getTestExplication() {
        return "Test draw boudingbox body";
    }
}
