package com.nzt.box.math.quadtree;

import com.badlogic.gdx.utils.Array;
import com.nzt.box.math.quadtree.pools.QuadTreeArrayPool;
import com.nzt.box.math.quadtree.pools.QuadTreePool;

public class QuadTreeHelper {

    private QuadTree quadTreeRoot;
    public QuadTreePool pool;
    public QuadTreeArrayPool quadArrayPool;

    public QuadTreeHelper(QuadTree quadTreeRoot) {
        this.quadTreeRoot = quadTreeRoot;
        this.quadArrayPool = new QuadTreeArrayPool();
        this.pool = new QuadTreePool();
    }


    public Array<QuadTree> getChildArray(QuadTree quadTree) {
        Array<QuadTree> array = quadArrayPool.obtain();
        array.add(quadTree.ne, quadTree.nw, quadTree.se, quadTree.sw);
        return array;
    }

}
