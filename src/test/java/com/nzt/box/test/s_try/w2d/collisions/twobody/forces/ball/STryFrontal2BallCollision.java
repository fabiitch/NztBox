package com.nzt.box.test.s_try.w2d.collisions.twobody.forces.ball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.mains.FastTesterMain;

public class STryFrontal2BallCollision extends BaseSTry2BallCollision {

    public STryFrontal2BallCollision(FastTesterMain main) {
        super(main);
//		createWallAroundScreen();
        afterClick(new Vector2(-200, 0));
        infoMsg("Click on screen to change balls positions", Color.RED);
    }

    @Override
    protected float getMass1() {
        return 1;
    }

    @Override
    protected float getMass2() {
        return 1;
    }

    @Override
    protected float getRestitution1() {
        return 1;
    }

    @Override
    protected float getRestitution2() {
        return 1;
    }

    @Override
    protected float getTransfert1() {
        return 1;
    }

    @Override
    protected float getTransfert2() {
        return 1;
    }

    @Override
    public void afterClick(Vector2 clickPos) {
        Vector2 clickPosInv = V2.inv(clickPos, new Vector2());
        ball1.setPosition(clickPos);
        ball2.setPosition(clickPosInv);

        Vector2 dirA = V2.directionTo(clickPos, Vector2.Zero, new Vector2());
        ball1.setVelocity(dirA.setLength(200));
        ball2.setVelocity(V2.inv(dirA));
    }


    @Override
    public String getTestExplication() {
        // TODO Auto-generated method stub
        return "Test collision balls";
    }

}
