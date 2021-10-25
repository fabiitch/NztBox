package com.nzt.box.debug;

import com.badlogic.gdx.graphics.Color;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

public class BoxDebugSettings {

    public boolean drawInactive = false;
    public boolean drawVelocity = true;
    public boolean drawBodyUserData = true;
    public boolean drawContactPoint = true;
    public boolean drawCenter = true;//TODO
    public boolean drawBounds = false;
    public boolean drawQuadTree = false;
    public boolean drawQuadTreeData = false;

    public Color colorDynamicBody = Color.CYAN;
    public Color colorKinematicBody = Color.CYAN;
    public Color colorStaticBody = Color.CYAN;
    public Color colorInactiveBody = Color.GRAY;

    public Color colorBoundingRect = Color.YELLOW;
    public Color colorVelocity = Color.GREEN;
    public Color colorContactPoint = Color.RED;

    public Color colorQuadTree = Color.PURPLE;

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
