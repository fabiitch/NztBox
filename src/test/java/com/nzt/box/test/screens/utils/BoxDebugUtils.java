package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.graphics.Color;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;

public class BoxDebugUtils {
    private final static String SEPARATOR = "--------";

    private final static String CONTACT_KEY_TITLE = "CollisionData";
    private final static String CONTACT_KEY_NORMAL = "DataNormal";
    private final static String CONTACT_KEY_FORCE = "DataForces";
    private final static String CONTACT_KEY_REBOUND = "DataRebound";
    private final static String CONTACT_KEY_SEPARATOR_START = "CollisionDataSeparatorStart";
    private final static String CONTACT_KEY_SEPARATOR_END = "CollisionDataSeparatorEnd";


    public static void toHud(int id, CollisionData data) {
        toHud(id, data, HudDebugPosition.BOT_LEFT, Color.WHITE);
    }

    public static void toHud(int id, CollisionData data, int hudDebugPosition, Color color) {
        HudDebug.add(CONTACT_KEY_SEPARATOR_START + id, SEPARATOR, SEPARATOR, hudDebugPosition, color);
        HudDebug.add(CONTACT_KEY_SEPARATOR_START + id, SEPARATOR, SEPARATOR, hudDebugPosition, color);
        HudDebug.add(CONTACT_KEY_TITLE + id, "Collision Data", "id=" + id, hudDebugPosition, color);
        HudDebug.add(CONTACT_KEY_NORMAL + id, "Normal", data.normal, hudDebugPosition, color);
        HudDebug.add(CONTACT_KEY_FORCE + id, "newVelA=" + data.newVelA, "forceOnB=" + data.newVelA, hudDebugPosition, color);
        HudDebug.add(CONTACT_KEY_REBOUND + id, "newVelB=" + data.newVelB, "reboundB=" + data.newVelB, hudDebugPosition, color);
        HudDebug.add(CONTACT_KEY_SEPARATOR_END + id, SEPARATOR, SEPARATOR, hudDebugPosition, color);
    }

    public static void remove(int id) {
        HudDebug.remove(CONTACT_KEY_SEPARATOR_START + id);
        HudDebug.remove(CONTACT_KEY_TITLE + id);
        HudDebug.remove(CONTACT_KEY_NORMAL + id);
        HudDebug.remove(CONTACT_KEY_FORCE + id);
        HudDebug.remove(CONTACT_KEY_REBOUND + id);
        HudDebug.remove(CONTACT_KEY_SEPARATOR_END + id);
    }


    private final static String BODY_DEF_SEPARATOR_START = "BodyDefSeparatorStart";
    private final static String BODY_DEF_KEY_NAME = "BodyDefName";
    private final static String BODY_DEF_SEPARATOR_END = "BodyDefSeparatorEND";
    private final static String BODY_DEF_KEY_MASS = "BodyDefMass";
    private final static String BODY_DEF_KEY_TRANSFERT = "BodyDefTransfert";
    private final static String BODY_DEF_KEY_RECEIVE = "BodyDefReceive";
    private final static String BODY_DEF_KEY_RESTITUTION = "BodyDefRestitution";

    public static void toHud(Body body, int positionOnStage) {
        toHud(new BodyDef().getFromBody(body), "Body " + body.userData, positionOnStage);
    }

    public static void toHud(BodyDef bodyDef, String name, int positionOnStage) {
        HudDebug.add(BODY_DEF_SEPARATOR_START + name, SEPARATOR, SEPARATOR, positionOnStage);
        HudDebug.add(BODY_DEF_KEY_NAME + name, "BodyDef", name, positionOnStage);
        HudDebug.add(BODY_DEF_KEY_MASS + name, "Mass", bodyDef.mass, positionOnStage);
        HudDebug.add(BODY_DEF_KEY_TRANSFERT + name, "Transfert", bodyDef.transfert, positionOnStage);
        HudDebug.add(BODY_DEF_KEY_RECEIVE + name, "Receive", bodyDef.receive, positionOnStage);
        HudDebug.add(BODY_DEF_KEY_RESTITUTION + name, "Restitution", bodyDef.restitution, positionOnStage);
        HudDebug.add(BODY_DEF_SEPARATOR_END + name, SEPARATOR, SEPARATOR, positionOnStage);
    }

    public static void remove(String name) {
        HudDebug.remove(BODY_DEF_SEPARATOR_START + name);
        HudDebug.remove(BODY_DEF_KEY_NAME + name);
        HudDebug.remove(BODY_DEF_KEY_MASS + name);
        HudDebug.remove(BODY_DEF_KEY_TRANSFERT + name);
        HudDebug.remove(BODY_DEF_KEY_RECEIVE + name);
        HudDebug.remove(BODY_DEF_KEY_RESTITUTION + name);
        HudDebug.remove(BODY_DEF_SEPARATOR_END + name);
    }
}
