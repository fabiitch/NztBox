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

    public void freeSub(QuadTree quadTree) {
        free(quadTree.ne);
        free(quadTree.nw);
        free(quadTree.se);
        free(quadTree.sw);
        quadTree.ne = quadTree.nw = quadTree.se = quadTree.sw = null;
    }

    public void initSubs(QuadTree quadTree) {
        quadTree.ne = container.poolQuads.obtain();
        quadTree.nw = container.poolQuads.obtain();
        quadTree.se = container.poolQuads.obtain();
        quadTree.sw = container.poolQuads.obtain();
    }

    @Override
    protected QuadTree newObject() {
        return new QuadTree(container);
    }
}
