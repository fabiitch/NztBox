package com.nzt.box.math.quadtree.pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.math.quadtree.QuadTree;

public class QuadTreeArrayPool extends Pool<Array<QuadTree>> {
    public QuadTreeArrayPool() {
    }

    public QuadTreeArrayPool(int initialCapacity) {
        super(initialCapacity);
    }

    public QuadTreeArrayPool(int initialCapacity, int max) {
        super(initialCapacity, max);
    }

    @Override
    public void free(Array<QuadTree> array) {
        array.clear();
        super.free(array);
    }

    @Override
    protected Array<QuadTree> newObject() {
        return new Array<>(4);
    }

}
