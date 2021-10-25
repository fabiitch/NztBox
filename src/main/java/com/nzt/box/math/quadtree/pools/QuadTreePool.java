package com.nzt.box.math.quadtree.pools;

import com.badlogic.gdx.utils.Pool;
import com.nzt.box.math.quadtree.QuadTree;

public class QuadTreePool extends Pool<QuadTree> {
    public QuadTreePool() {
    }

    public QuadTreePool(int initialCapacity) {
        super(initialCapacity);
    }

    public QuadTreePool(int initialCapacity, int max) {
        super(initialCapacity, max);
    }

    @Override
    protected QuadTree newObject() {
        return new QuadTree();
    }
}
