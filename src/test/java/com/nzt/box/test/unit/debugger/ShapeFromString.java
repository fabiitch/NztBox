package com.nzt.box.test.unit.debugger;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

 class ShapeFromString {

    public static Rectangle rectangle(String s) {
        return new Rectangle().fromString(s);
    }

    public static Circle circle(String s) {
        String[] split = s.split(",");
        return new Circle(Float.parseFloat(split[0]),
                Float.parseFloat(split[1]),
                Float.parseFloat(split[2]));
    }

    public static Polygon polygon(String s) {
        s = s.substring(1, s.length() - 1);
        String[] split = s.split(",");
        float[] vertices = new float[split.length];
        for (int i = 0; i < split.length; i++)
            vertices[i] = Float.parseFloat(split[i]);
        return new Polygon(vertices);
    }
}
