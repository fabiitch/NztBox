package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Fixture;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;

public class QuadTreeUtils {
    private static Rectangle rectTmp = new Rectangle(); //TODo pas static

    private QuadTreeUtils() {

    }

    public static Vector2 getSizeChild(Rectangle rect, int maxDepth) {
        return new Vector2(getSizeChild(rect.width, maxDepth), getSizeChild(rect.height, maxDepth));
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

    public static boolean splitContainsRect(Rectangle container, Rectangle rectInside) {
        if (rectInside.width > container.width / 2 || rectInside.height > container.height / 2)
            return false;
        return RectangleUtils.containsStick(getNW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSW(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getNE(container, rectTmp), rectInside)
                || RectangleUtils.containsStick(getSE(container, rectTmp), rectInside);
    }

    public static int countValuesCantSplitted(QuadTree quadTree) {
        int count = 0;
        for (int i = 0, n = quadTree.valuesCount; i < n; i++) {
            Fixture fixture = quadTree.values[i];
            if (QuadTreeUtils.splitContainsRect(quadTree.boundingRect, fixture.getBoundingRectangle()))
                count++;

        }
        return count;
    }


}
