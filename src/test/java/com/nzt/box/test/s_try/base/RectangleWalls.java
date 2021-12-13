package com.nzt.box.test.s_try.base;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.world.World;
import com.nzt.gdx.math.shapes.Segment;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.math.vectors.V2;

/**
 * Create 4 wall around a rect
 */
public class RectangleWalls {

    public Body botWall, topWall, rightWall, leftWall;
    public Array<Body> walls;
    public BodyDef bodyDef = new BodyDef(BodyType.Static).mass(1).transfert(1).receive(1).restitution(1);
    private World world;

    public RectangleWalls(Rectangle rect, float sizeWalls, World world) {
        this.world = world;
        walls = new Array<>();
        create(rect, sizeWalls);
    }

    private Body createWall(Rectangle rect, String userData) {
        Vector2 center = RectangleUtils.getCenter(rect, V2.tmp);
        Body wall = new Body(bodyDef);
        wall.userData = userData;
        RectangleShape shapeBot = new RectangleShape(rect);
        Fixture fixture = new Fixture(shapeBot);
        fixture.userData = "F " + userData;
        wall.addFixture(fixture);
        wall.setPosition(center);
        world.addBody(wall);
        return wall;
    }

    public void create(Rectangle rect, float sizeWalls) {
        Rectangle[] rectsAround = RectangleUtils.getRectsAround(rect, sizeWalls);
        botWall = createWall(rectsAround[0], "Wall Bot");
        topWall = createWall(rectsAround[1], "Wall Top");
        leftWall = createWall(rectsAround[2], "Wall Left");
        rightWall = createWall(rectsAround[3], "Wall Right");
        walls.add(topWall, botWall, rightWall, leftWall);
    }

    public void removeWalls() {
        world.removeBody(botWall);
        world.removeBody(topWall);
        world.removeBody(rightWall);
        world.removeBody(leftWall);
    }

}
