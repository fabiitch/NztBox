package com.nzt.box.debug;

import com.badlogic.gdx.graphics.Color;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

public class BoxDebugSettings {

    public boolean drawInactive = false;
    public boolean drawVelocity = true;
    public boolean drawBodyUserData = true;

    public boolean drawContactPoint = true;
    public Color colorContactPoint = Color.RED;
    public boolean drawContactNormal = false;
    public Color colorContactNormal = Color.BLUE;

    public boolean drawCenter = true;//TODO

    public boolean drawBoudingsBoxFixtures = false;
    public Color colorBoudingBoxFixture = Color.YELLOW;

    public boolean drawBoudingsBoxBodies = false;
    public Color colorBoudingBoxBodies = Color.BROWN;

    public boolean drawQuadTree = false;
    public Color colorQuadTree = Color.PURPLE;

    public boolean drawQuadTreeData = false;

    public Color colorDynamicBody = Color.CYAN;
    public Color colorKinematicBody = Color.CYAN;
    public Color colorStaticBody = Color.GRAY;
    public Color colorInactiveBody = Color.GRAY;


    public Color colorVelocity = Color.GREEN;


    public BoxDebugSettings() {
    }

    public Color getColorBody(Body body) {
        if (!body.active) {
            return colorInactiveBody;
        }
        if (body.bodyType == BodyType.Static)
            return colorStaticBody;
        if (body.bodyType == BodyType.Kinematic)
            return colorKinematicBody;
        return colorDynamicBody;
    }
}
