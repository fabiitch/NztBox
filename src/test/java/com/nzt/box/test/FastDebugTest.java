package com.nzt.box.test;

import com.badlogic.gdx.math.Rectangle;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import org.junit.jupiter.api.Test;

public class FastDebugTest {
    @Test
    public void ff() {
        Rectangle root = new Rectangle().fromString("[-400.0,-343.0,9925.0,593.0]");
        Rectangle fixture = new Rectangle().fromString("[10327.5,97.9202,12989.699,48.959373]");

        RectangleUtils.mergeFloorCeil(root, fixture);
        if (!RectangleUtils.containsStick(root, fixture)) {
            System.out.println("pb");
        }
    }


}
