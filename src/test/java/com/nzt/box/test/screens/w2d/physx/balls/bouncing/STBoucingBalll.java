package com.nzt.box.test.screens.w2d.physx.balls.bouncing;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.balls.bouncing")
public class STBoucingBalll extends Box2dTestScreen {
    Body ballBody;

    public STBoucingBalll(FastTesterMain main) {
        super(main);

        ballBody = new Body(BodyType.Dynamic);
        Circle circle = new Circle(0, 0, 50);
        CircleShape shape = new CircleShape(circle);
        Fixture fixture = new Fixture(shape);
        ballBody.addFixture(fixture);
        world.addBody(ballBody);
        ballBody.mass = 1;
        ballBody.restitution = 1;
        ballBody.transfert = 1;

        createWallAroundScreen();
        HudDebug.addTopRight("Wall restitution :", screenWalls.leftWall.restitution);

        infoMsg("ballBody.mass = " + ballBody.mass);
        infoMsg("ballBody.restitution = " + ballBody.restitution);
        infoMsg("ballBody.transfert = " + ballBody.transfert);
        ballBody.setVelocity(200, 200);
    }

    @Override
    public void doRender(float dt) {
        debugMsg("Velocity :", ballBody.getVelocity(V2.tmp));
    }

    @Override
    public String getTestExplication() {
        return "Ball rebound test";
    }
}
