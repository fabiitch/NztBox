package com.nzt.box.test.screens.w2d.balls.collisions;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STBallsTwoDirCollision extends BaseBallCollision {
    public STBallsTwoDirCollision(FastTesterMain main) {
        super(main);
        resetPos();

        world.contactListener = new ContactListener() {
            @Override
            public void beginContact(ContactFixture contactFixture) {

            }

            @Override
            public void endContact(ContactFixture contactFixture) {

            }

            @Override
            public void continueContact(ContactFixture contactFixture) {

            }

            @Override
            public void preSolve(ContactFixture contactFixture) {

            }

            @Override
            public void postSolve(ContactFixture contactFixture) {

            }
        };
    }

    public void resetPos() {
        ball1.setPosition(-200, 0);
        ball2.setPosition(0, -200);

        ball1.setVelocity(200, 0);
        ball2.setVelocity(0, 205);
    }


    @Override
    public void afterClick(Vector2 clickPos) {
        resetPos();
    }

    @Override
    public String getTestExplication() {
        return "One ball should give force, second body start move at collision";
    }
}
