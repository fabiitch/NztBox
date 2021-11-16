package com.nzt.box.test.screens.base;

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

    public RectangleWalls(Rectangle rect, float size, World world) {
        this.world = world;
        walls = new Array<>();
        create(rect, size);
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

    public void create(Rectangle rect, float size) {
        final int decal = 5;

        Segment ab = RectangleUtils.getAB(rect, new Segment());
        Rectangle rectAB = new Rectangle(ab.a.x, ab.a.y - size + decal, rect.width, size);
        botWall = createWall(rectAB, "Wall Bot");

        Segment bc = RectangleUtils.getBC(rect, new Segment());
        Rectangle rectBC = new Rectangle(bc.a.x - decal, bc.a.y, size, rect.height);
        rightWall = createWall(rectBC, "Wall Right");
//
        Segment cd = new Segment(RectangleUtils.getD(rect, new Vector2()), RectangleUtils.getC(rect, new Vector2()));
        Rectangle rectCD = new Rectangle(cd.a.x, cd.a.y - decal, cd.dst(), size);
        topWall = createWall(rectCD, "Wall Top");

        Segment ad = RectangleUtils.getAD(rect, new Segment());
        Rectangle rectAD = new Rectangle(ad.a.x - size + decal, ad.a.y, size, ad.dst());
        leftWall = createWall(rectAD, "Wall Left");
//
        walls.add(topWall, botWall, rightWall, leftWall);
    }

    public void removeWalls() {
        world.removeBody(botWall);
        world.removeBody(topWall);
        world.removeBody(rightWall);
        world.removeBody(leftWall);
    }

}
