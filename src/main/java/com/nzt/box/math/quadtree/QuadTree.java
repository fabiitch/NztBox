package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.utils.ArrayUtils;

import java.util.Arrays;

public class QuadTree implements Pool.Poolable {

    public QuadTree nw, ne, sw, se;
    public QuadTree parent;

    public int maxValues;
    public int maxDepth;

    public Rectangle boundingRect = new Rectangle();
    private Vector2 tmp1 = new Vector2();

    public Fixture<?>[] values;
    public int depth;
    public int valuesCount;
    public boolean isSplitted;


    public QuadTreeHelper helper;

    public QuadTree() {

    }

    public int countSubValues() {
        int count = 0;
        if (!isSplitted) {
            return valuesCount;
        } else {
            Array<QuadTree> childArray = helper.getChildArray(this);
            for (QuadTree sub : childArray)
                count += sub.countSubValues();
            helper.freeChildArray(childArray);
        }
        return count;
    }

    public Array<Fixture<?>> getSubValues(Array<Fixture<?>> fixtureArray) {
        if (!isSplitted) {
            for (int i = 0; i < valuesCount; i++) {
                fixtureArray.add(values[i]);
            }
            return fixtureArray;
        } else {
            Array<QuadTree> childArray = helper.getChildArray(this);
            for (QuadTree sub : childArray) {
                sub.getSubValues(fixtureArray);
            }
            return fixtureArray;
        }
    }

    public int countSubQuad() {
        int count = 0;
        if (!isSplitted) {
            return 0;
        } else {
            Array<QuadTree> childArray = helper.getChildArray(this);
            for (QuadTree sub : childArray)
                count += sub.isSplitted ? sub.countSubQuad() : 1;

        }
        return count;
    }

    @Override
    public void reset() {
        if (ne != null)
            helper.quadPool.free(ne);
        if (nw != null)
            helper.quadPool.free(nw);
        if (se != null)
            helper.quadPool.free(se);
        if (sw != null)
            helper.quadPool.free(sw);
        valuesCount = 0;
        isSplitted = false;
        if (values.length > maxValues) values = new Fixture[maxValues];
    }

    public QuadTree init(QuadTreeHelper helper, Rectangle rect, int maxValues, int maxDepth) {
        return this.init(helper, null, rect, 0, maxValues, maxDepth);
    }

    private QuadTree init(QuadTreeHelper helper, QuadTree parent, Rectangle rect, int depth, int maxValues,
                          int maxDepth) {
        this.helper = helper;
        this.parent = parent;
        this.boundingRect.set(rect);
        this.depth = depth;
        this.maxValues = maxValues;
        this.maxDepth = maxDepth;
        this.values = new Fixture[maxValues];
        return this;
    }

    public void regroup() {
        if (!isSplitted)
            return;

        ne.regroup();
        for (Fixture<?> value : ne.values) {
            if (value != null)
                addValue(value);
        }
        helper.quadPool.free(ne);
        this.ne = null;

        nw.regroup();
        for (Fixture<?> value : nw.values) {
            if (value != null)
                addValue(value);
        }
        helper.quadPool.free(nw);
        this.nw = null;

        sw.regroup();
        for (Fixture<?> value : sw.values) {
            if (value != null)
                addValue(value);
        }
        helper.quadPool.free(sw);
        this.sw = null;

        se.regroup();
        for (Fixture<?> value : se.values) {
            if (value != null)
                addValue(value);
        }
        helper.quadPool.free(se);
        this.se = null;

        this.isSplitted = false;
    }

    public void split() {
        if (isSplitted)
            return;
        float halfWitdh = boundingRect.width / 2;
        float halfHeight = boundingRect.height / 2;
        Rectangle rectSW = new Rectangle(boundingRect.x, boundingRect.y, halfWitdh, halfHeight);
        Rectangle rectSE = new Rectangle(boundingRect.x + halfWitdh, boundingRect.y, halfWitdh, halfHeight);
        Rectangle rectNW = new Rectangle(boundingRect.x, boundingRect.y + halfHeight, halfWitdh, halfHeight);
        Rectangle rectNE = new Rectangle(boundingRect.x + halfWitdh, boundingRect.y + halfHeight, halfWitdh, halfHeight);

        final QuadTree SW = helper.quadPool.obtain();
        this.sw = SW.init(this.helper, this, rectSW, this.depth + 1, this.maxValues, this.maxDepth);

        final QuadTree SE = helper.quadPool.obtain();
        this.se = SE.init(this.helper, this, rectSE, this.depth + 1, this.maxValues, this.maxDepth);

        final QuadTree NW = helper.quadPool.obtain();
        this.nw = NW.init(this.helper, this, rectNW, this.depth + 1, this.maxValues, this.maxDepth);

        final QuadTree NE = helper.quadPool.obtain();
        this.ne = NE.init(this.helper, this, rectNE, this.depth + 1, this.maxValues, this.maxDepth);

        isSplitted = true;
        for (int i = 0, n = valuesCount; i < n; i++) {
            Fixture fixture = values[i];
            addFixture(fixture);
            values[i] = null;
        }
        valuesCount = 0;
    }

    public QuadTree getQuadTree(Vector2 pos) {
        return getQuadTree(pos.x, pos.y);
    }

    public QuadTree getQuadTree(float x, float y) {
        if (this.boundingRect.contains(x, y)) {
            Vector2 center = RectangleUtils.getCenter(boundingRect, tmp1);
            boolean xSup = x > center.x;
            boolean ySup = y > center.y;
            if (xSup)
                if (ySup)
                    return ne != null ? ne.getQuadTree(x, y) : this;
                else
                    return se != null ? se.getQuadTree(x, y) : this;

            else if (ySup)
                return nw != null ? nw.getQuadTree(x, y) : this;
            else
                return sw != null ? sw.getQuadTree(x, y) : this;

        }
        return null;
    }

    public void moveBody(Body body) {
//        Array<Fixture<?>> fixtures = body.fixtures;
//        removeBody(body);
//        for (Fixture fixture : fixtures)
//            addFixture(fixture);
    }

    public void addBody(Body body) {
        Array<Fixture<?>> fixtures = body.fixtures;
        for (Fixture fixture : fixtures)
            addFixture(fixture);
    }

    public void removeBody(Body body) {
        for (Fixture fixture : body.fixtures) {
            Rectangle rectangle = fixture.bodyShape.computeBoundingRect();
            Vector2 posVertexRect;
            QuadTree quadContains;

            posVertexRect = RectangleUtils.getA(rectangle, tmp1);
            quadContains = getQuadTree(posVertexRect.x, posVertexRect.y);
            if (quadContains != null)
                quadContains.removeValue(fixture);

            posVertexRect = RectangleUtils.getB(rectangle, tmp1);
            quadContains = getQuadTree(posVertexRect.x, posVertexRect.y);
            if (quadContains != null)
                quadContains.removeValue(fixture);

            posVertexRect = RectangleUtils.getC(rectangle, tmp1);
            quadContains = getQuadTree(posVertexRect.x, posVertexRect.y);
            if (quadContains != null)
                quadContains.removeValue(fixture);

            posVertexRect = RectangleUtils.getD(rectangle, tmp1);
            quadContains = getQuadTree(posVertexRect.x, posVertexRect.y);
            if (quadContains != null)
                quadContains.removeValue(fixture);
        }
    }

    private void addFixture(Fixture fixture) {
        Array<QuadTree> tmpQuadArray = helper.quadArrayPool.obtain();
        tmpQuadArray.clear();
        Rectangle rectangle = fixture.bodyShape.computeBoundingRect();
        QuadTree quadTreeToAdd;

        quadTreeToAdd = getQuadTree(RectangleUtils.getA(rectangle, tmp1));
        //TODO le else avec resize
        if (quadTreeToAdd != null)
            tmpQuadArray.add(quadTreeToAdd);

        quadTreeToAdd = getQuadTree(RectangleUtils.getB(rectangle, tmp1));
        if (quadTreeToAdd != null)
            if (!tmpQuadArray.contains(quadTreeToAdd, true))
                tmpQuadArray.add(quadTreeToAdd);


        quadTreeToAdd = getQuadTree(RectangleUtils.getC(rectangle, tmp1));
        if (quadTreeToAdd != null)
            if (!tmpQuadArray.contains(quadTreeToAdd, true))
                tmpQuadArray.add(quadTreeToAdd);

        quadTreeToAdd = getQuadTree(RectangleUtils.getD(rectangle, tmp1));
        if (quadTreeToAdd != null)
            if (!tmpQuadArray.contains(quadTreeToAdd, true))
                tmpQuadArray.add(quadTreeToAdd);

        if (tmpQuadArray.size == 0) {
            this.addValue(fixture);
        } else {
            for (QuadTree quadTree : tmpQuadArray) {
                quadTree.addValue(fixture);
            }
        }
        helper.quadArrayPool.free(tmpQuadArray);
    }

    public void checkIfShouldRegroup() {
        if (countSubQuad() == 4)
            if (countSubValues() <= maxValues / 2)
                regroup();
    }

    public void clearValues() {
        if (values.length > maxValues) {
            values = new Fixture[maxValues];
        } else {
            for (int i = 0, n = valuesCount; i < n; i++) {
                values[i] = null;
            }
        }
        valuesCount = 0;
    }

    private void removeValue(Fixture fixture) {
        int index = -1;
        for (int i = 0, n = valuesCount; i < n; i++) {
            if (values[i] == fixture) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            ArrayUtils.removeAndDecal(values, index);
            valuesCount--;
        }
        if (depth > 0) parent.checkIfShouldRegroup();
    }

    private void addValue(Fixture fixture) {
        if (valuesCount + 1 >= maxValues && this.depth < maxDepth)
            split();
        else {
            if (valuesCount >= maxValues)
                values = Arrays.copyOf(values, valuesCount + 10);
            values[valuesCount++] = fixture;
        }
    }


}
