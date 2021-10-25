package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;

public class QuadTreeUtils {

    public static Rectangle getSizeChild(Rectangle rect, int maxDepth) {
        return new Rectangle(0, 0, getSizeChild(rect.width, maxDepth), getSizeChild(rect.height, maxDepth));
    }

    public static float getSizeChild(float sizeStart, int depth) {
        for (int i = 0; i < depth; i++) {
            sizeStart /= 2;
        }
        return sizeStart;
    }



}
