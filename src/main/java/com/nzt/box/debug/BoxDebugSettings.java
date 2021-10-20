package com.nzt.box.debug;

public class BoxDebugSettings {

    public boolean drawInactive;
    public boolean drawVelocity;
    public boolean drawBodyUserData;
    public boolean drawContactPoint;
    public boolean drawCenter;//TODO

    public BoxDebugSettings() {
        drawInactive = true;
        drawVelocity = true;
        drawContactPoint = true;
        drawBodyUserData = true;
//        drawCenter = true;
    }
}
