package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.math.quadtree.pools.FixtureArrayPool;
import com.nzt.box.math.quadtree.pools.QuadTreeArrayPool;
import com.nzt.box.math.quadtree.pools.QuadTreePool;

public class QuadTreeContainer {
    public QuadTree root;

    public QuadTreePool quadPool;
    public QuadTreeArrayPool quadArrayPool;
    public FixtureArrayPool fixtureArrayPool;


    public int maxValues;
    public int maxDepth;

    public Vector2 tmp1 = new Vector2(); //TODO multithread

    public QuadTreeContainer(Rectangle rect, int maxValues, int maxDepth) {
        this.quadArrayPool = new QuadTreeArrayPool();
        this.quadPool = new QuadTreePool(this);
        this.fixtureArrayPool = new FixtureArrayPool();
        this.init(rect, maxValues, maxDepth);
    }

    public QuadTreeContainer() {
        this(new Rectangle(), 10, 5);
    }

    public QuadTree init(Rectangle rect, int maxValues, int maxDepth) {
        if (root != null)
            quadPool.free(root);
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
        QuadTree quadRoot = quadPool.obtain();
        this.root = quadRoot.init(null, rect, 0);
        return this.root;
    }

    public Array<QuadTree> getTmpChildArray(QuadTree quadTree) {
        Array<QuadTree> array = quadArrayPool.obtain();
        array.add(quadTree.ne, quadTree.nw, quadTree.se, quadTree.sw);
        return array;
    }

    public void freeChildArray(Array<QuadTree> toFree) {
        quadArrayPool.free(toFree);
    }

    public void addBody(Body body) {
        Array<Fixture<?>> fixtures = body.fixtures;
        for (Fixture fixture : fixtures)
            root.addFixture(fixture);
    }

    public void removeBody(Body body) {
        Array<Fixture<?>> fixtures = body.fixtures;
        for (Fixture fixture : fixtures)
            root.removeFixture(fixture);
    }

    public void rebuild(Rectangle rectangleStart, int newMaxDepth) {
        Array<Fixture<?>> allValues = root.getValuesAndSub(fixtureArrayPool.obtain());
        init(rectangleStart, maxValues, newMaxDepth);
        for (Fixture<?> fixture : allValues)
            root.addFixture(fixture);
        fixtureArrayPool.free(allValues);
    }


    public void growQuadTree(Fixture fixture) {
        Rectangle rectangleFixture = fixture.getBoundingRectangle();
        Rectangle rectRoot = root.boundingRect;
        rectRoot.merge(rectangleFixture);

        rebuild(rectRoot, this.maxDepth++);
        root.addFixture(fixture);
    }

    public void moveBody(Body body) {
        Array<Fixture<?>> fixtures = body.fixtures;
        for (Fixture fixture : fixtures){
            Rectangle boundingRectangleBefore = fixture.getBoundingRectangle();
        }
    }
}
