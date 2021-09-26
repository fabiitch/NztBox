package com.nzt.box.test.screens.base;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.world.World;

public class ScreenWalls {

    private final static float SCREEN_WITDH = BoxTestScreen.SCREEN_WITDH;
    private final static float SCREEN_HEIGHT = BoxTestScreen.SCREEN_HEIGHT;

    public Body botWall, topWall, rightWall, leftWall;
    public Array<Body> walls;
    public BodyDef bodyDef;
    private World world;

    public ScreenWalls(World world) {
        this(world, new BodyDef(BodyType.Static).mass(1).transfert(1).receive(1).restitution(1));
    }

    public ScreenWalls(World world, BodyDef bodyDef) {
        this.world = world;
        this.bodyDef = bodyDef;
        walls = new Array<>();
        create();
    }

    public void removeWalls() {
        world.destroyBody(botWall);
        world.destroyBody(topWall);
        world.destroyBody(rightWall);
        world.destroyBody(leftWall);
    }

    public void create() {
        /**
         * D-----C
         * -------
         * A-----B
         */
        float aX = -SCREEN_WITDH / 2 + 3, aY = -SCREEN_HEIGHT / 2 + 1;
        float bX = SCREEN_WITDH / 2 - 1, bY = -SCREEN_HEIGHT / 2 + 1;
        float cX = SCREEN_WITDH / 2 - 1, cY = SCREEN_HEIGHT / 2 - 1;
        float dX = -SCREEN_WITDH / 2 + 1, dY = SCREEN_HEIGHT / 2 - 1;

        botWall = new Body(bodyDef);
        botWall.userData = "WallBot";
        RectangleShape shapeBot = new RectangleShape(SCREEN_WITDH, 10);
        Fixture fixtureBot = new Fixture(shapeBot);
        fixtureBot.userData = "F WallBot";
        botWall.addFixture(fixtureBot);
        world.addBody(botWall);
        botWall.setPosition(0, aY);

        topWall = new Body(bodyDef);
        topWall.userData = "WallTop";
        RectangleShape shapeTop = new RectangleShape(SCREEN_WITDH, 10);
        Fixture fixtureTop = new Fixture(shapeTop);
        fixtureTop.userData = "F WallTop";
        topWall.addFixture(fixtureTop);
        world.addBody(topWall);
        topWall.setPosition(0, cY);

        rightWall = new Body(bodyDef);
        rightWall.userData = "WallRight";
        RectangleShape shapeRight = new RectangleShape(10, SCREEN_HEIGHT);
        Fixture fixtureRight = new Fixture(shapeRight);
        fixtureRight.userData = "F WallRight";
        rightWall.addFixture(fixtureRight);
        world.addBody(rightWall);
        rightWall.setPosition(-SCREEN_WITDH / 2, 0);

        leftWall = new Body(bodyDef);
        leftWall.userData = "WallLeft";
        RectangleShape shapeLeft = new RectangleShape(10, SCREEN_HEIGHT);
        Fixture fixtureLeft = new Fixture(shapeLeft);
        fixtureLeft.userData = "F WallLeft";
        leftWall.addFixture(fixtureLeft);
        world.addBody(leftWall);
        leftWall.setPosition(SCREEN_WITDH / 2, 0);


        walls.add(topWall, botWall, rightWall, leftWall);
    }
}
