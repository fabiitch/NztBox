package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.detector.impl.ContactCircle;
import com.nzt.box.contact.detector.impl.ContactRectangle;
import com.nzt.box.math.quadtree.pools.FixtureArrayPool;
import com.nzt.box.math.quadtree.pools.QuadTreeArrayPool;
import com.nzt.box.math.quadtree.pools.QuadTreePool;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;

public class QuadTreeContainer {
    public QuadTree root;

    public QuadTreePool poolQuads;
    public QuadTreeArrayPool poolQuadArray;
    public FixtureArrayPool poolFixtureArray;

    public int maxValues;
    public int maxDepth;

    public Vector2 tmp1 = new Vector2(); //TODO multithread


    public QuadTreeContainer(Rectangle rect, int maxValues, int maxDepth) {
        this.poolQuadArray = new QuadTreeArrayPool();
        this.poolQuads = new QuadTreePool(this);
        this.poolFixtureArray = new FixtureArrayPool();
        this.init(rect, maxValues, maxDepth);
    }

    public QuadTreeContainer() {
        this(new Rectangle(), 10, 5);
    }

    public QuadTree init(Rectangle rect, int maxValues, int maxDepth) {
        if (root != null)
            poolQuads.free(root);
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
        QuadTree quadRoot = poolQuads.obtain();
        this.root = quadRoot.init(null, rect, 0);
        return this.root;
    }

    public Array<QuadTree> getTmpChildArray(QuadTree quadTree) {
        Array<QuadTree> array = poolQuadArray.obtain();
        array.add(quadTree.ne, quadTree.nw, quadTree.se, quadTree.sw);
        return array;
    }

    public void freeChildArray(Array<QuadTree> toFree) {
        poolQuadArray.free(toFree);
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

    public void moveBody(Body body) {
        Array<Fixture<?>> fixtures = body.fixtures;
        for (Fixture fixture : fixtures) {
            QuadTree quadTreeBefore = fixture.quadTree;
            Rectangle boundingRectangle = fixture.getBoundingRectangle();
            QuadTree newQuadTree = root.getQuadTree(boundingRectangle);
            if (newQuadTree == null) {
                growQuadTree(fixture);
                newQuadTree = root.getQuadTree(boundingRectangle);
            }
            if (quadTreeBefore != newQuadTree) {
                quadTreeBefore.removeValue(fixture);
                newQuadTree.addFixture(fixture);
            }
        }
    }

    public void rebuild(Rectangle rectangleStart, int newMaxDepth) {
        Array<Fixture<?>> allValues = root.getValuesAndSub(poolFixtureArray.obtain());
        init(rectangleStart, maxValues, newMaxDepth);
        for (Fixture<?> fixture : allValues)
            root.addFixture(fixture);
        poolFixtureArray.free(allValues);
    }


    public void growQuadTree(Fixture fixture) {
        Rectangle rectFixture = fixture.getBoundingRectangle();
        Rectangle rectRoot = root.boundingRect;
        Rectangle tmpRoot = new Rectangle(rectRoot);
        RectangleUtils.mergeFloorCeil(rectRoot, rectFixture);
        if (!RectangleUtils.containsStick(rectRoot, rectFixture)) {
            System.out.println("=========Pb ");
            System.out.println("rootBefore=" + tmpRoot);
            System.out.println("rectRoot=" + rectRoot);
            System.out.println("rectFixture=" + rectFixture);
            System.out.println("=========Pb ");
            System.exit(0);
        }

        rebuild(rectRoot, this.maxDepth++);
        root.addFixture(fixture);
    }


    private ContactRectangle contactRectangle = new ContactRectangle();
    private ContactCircle contactCircle = new ContactCircle();

    public Array<Fixture<?>> getFixtures(Rectangle rect) {
        QuadTree quadTree = root.getQuadTree(rect);
        if (quadTree == null) {
            quadTree = root;
        }
        Array<Fixture<?>> valuesAndSub = quadTree.getValuesAndSub(poolFixtureArray.obtain());
        contactRectangle.myRectangle.set(rect);

        Array<Fixture<?>> result = poolFixtureArray.obtain();
        for (Fixture fixture : valuesAndSub) {
            if (fixture.bodyShape.testContact(contactRectangle)) {
                result.add(fixture);
            }
        }
        return result;
    }

}
