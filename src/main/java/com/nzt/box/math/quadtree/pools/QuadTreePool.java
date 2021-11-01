package com.nzt.box.math.quadtree.pools;

import com.badlogic.gdx.utils.Pool;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeContainer;

public class QuadTreePool extends Pool<QuadTree> {

    private QuadTreeContainer container;

    public QuadTreePool(QuadTreeContainer container) {
        super(50);
        this.container = container;
    }

    @Override
    protected QuadTree newObject() {
        return new QuadTree(container);
    }
}
