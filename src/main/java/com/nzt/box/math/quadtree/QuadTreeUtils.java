package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;

public class QuadTreeUtils {
    private static Rectangle rectTmp = new Rectangle(); //TODo pas static

    public static Rectangle getSizeChild(Rectangle rect, int maxDepth) {
        return new Rectangle(0, 0, getSizeChild(rect.width, maxDepth), getSizeChild(rect.height, maxDepth));
    }

    public static float getSizeChild(float sizeStart, int depth) {
        for (int i = 0; i < depth; i++) {
            sizeStart /= 2;
        }
        return sizeStart;
    }

    public static Rectangle getNW(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x, parent.y + halfHeight, halfWitdh, halfHeight);
    }

    public static Rectangle getSW(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x, parent.y, halfWitdh, halfHeight);
    }

    public static Rectangle getNE(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x + halfWitdh, parent.y + halfHeight, halfWitdh, halfHeight);
    }

    public static Rectangle getSE(Rectangle parent, Rectangle child) {
        float halfWitdh = parent.width / 2;
        float halfHeight = parent.height / 2;
        return child.set(parent.x + halfWitdh, parent.y, halfWitdh, halfHeight);
    }


    public static boolean canRectCanBeSplitted(Rectangle container, Rectangle rectInside) {
        return RectangleUtils.containsStick(getNW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getNE(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSE(container, rectTmp), rectInside);
    }


}
