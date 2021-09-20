package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.graphics.Color;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;

public class BoxDebugUtils {

    private final static String KEY_TITLE = "CollisionData";
    private final static String KEY_NORMAL = "DataNormal";
    private final static String KEY_FORCE = "DataForces";
    private final static String KEY_REBOUND = "DataRebound";
    private final static String KEY_SEPARATOR_START = "CollisionDataSeparatorStart";
    private final static String KEY_SEPARATOR_END = "CollisionDataSeparatorEnd";
    private final static String SEPARATOR = "--------------";

    public static void toHud(int id, CollisionData data) {
        toHud(id, data, HudDebugPosition.BOT_LEFT, Color.WHITE);
    }

    public static void toHud(int id, CollisionData data, int hudDebugPosition, Color color) {
        HudDebug.add(KEY_SEPARATOR_START + id, SEPARATOR, SEPARATOR, hudDebugPosition, color);
        HudDebug.add(KEY_TITLE + id, "Collision Data", "id=" + id, hudDebugPosition, color);
        HudDebug.add(KEY_NORMAL + id, "Normal", data.normal, hudDebugPosition, color);
        HudDebug.add(KEY_FORCE + id, "forceOnA=" + data.forceOnA, "forceOnB=" + data.forceOnB, hudDebugPosition, color);
        HudDebug.add(KEY_REBOUND + id, "reboundA=" + data.reboundA, "reboundB=" + data.reboundB, hudDebugPosition, color);
        HudDebug.add(KEY_SEPARATOR_END + id, SEPARATOR, SEPARATOR, hudDebugPosition, color);
    }

    public static void remove(int id) {
        HudDebug.remove(KEY_SEPARATOR_START + id);
        HudDebug.remove(KEY_TITLE + id);
        HudDebug.remove(KEY_NORMAL + id);
        HudDebug.remove(KEY_FORCE + id);
        HudDebug.remove(KEY_REBOUND + id);
        HudDebug.remove(KEY_SEPARATOR_END + id);
    }
}
