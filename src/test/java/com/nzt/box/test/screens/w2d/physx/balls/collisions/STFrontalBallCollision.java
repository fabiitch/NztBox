package com.nzt.box.test.screens.w2d.physx.balls.collisions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.math.vectors.V2;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;

public class STFrontalBallCollision extends BaseBallCollision {


    public STFrontalBallCollision(FastTesterMain main) {
        super(main);
//		createWallAroundScreen();
        afterClick(new Vector2(200, 0));
        infoMsg("Click on screen to change balls positions", Color.RED);
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
