package com.nzt.box.test.screens.w2d.physx.bouncing;

import com.badlogic.gdx.math.Circle;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx.bouncing")
public abstract class BaseSTOneBodyBouncing extends Box2dTestScreen {
    protected Body body;

    public BaseSTOneBodyBouncing(FastTesterMain main) {
        super(main);
        body = new Body(bodyDef());
        Fixture fixture = new Fixture(bodyShape());
        body.addFixture(fixture);
        world.addBody(body);

        createWallAroundScreen();
        HudDebug.addTopRight("Wall restitution :", screenWalls.leftWall.restitution);

        infoMsg("ballBody.mass = " + body.mass);
        infoMsg("ballBody.restitution = " + body.restitution);
        infoMsg("ballBody.transfert = " + body.transfert);
        body.setVelocity(200, 200);
    }

    protected abstract BodyDef bodyDef();

    protected abstract BodyShape bodyShape();

    @Override
    public void doRender(float dt) {
        debugMsg("Velocity :", body.getVelocity(V2.tmp));
    }

    @Override
    public String getTestExplication() {
        return "One shape rebound test";
    }
}
