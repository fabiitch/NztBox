package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Fixture;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.utils.ArrayUtils;

import java.util.Arrays;

public class QuadTree implements Pool.Poolable {
    public QuadTree parent, nw, ne, sw, se;
    public Rectangle boundingRect;
    public Fixture<?>[] values;
    public int valuesCount;
    public int depth;

    public QuadTreeContainer container;

    public QuadTree(QuadTreeContainer container) {
        this.container = container;
        this.boundingRect = new Rectangle();
        this.values = new Fixture[container.maxValues];
    }

    @Override
    public void reset() {
        if (ne != null) {
            container.poolQuads.free(ne);
            this.ne = null;
        }
        if (nw != null) {
            container.poolQuads.free(nw);
            this.nw = null;
        }
        if (se != null) {
            container.poolQuads.free(se);
            this.se = null;
        }

        if (sw != null) {
            container.poolQuads.free(sw);
            this.sw = null;
        }
        valuesCount = 0;
        depth = 0;
        clearValues();
    }

    public QuadTree init(QuadTree parent, Rectangle rect, int depth) {
        this.parent = parent;
        this.boundingRect.set(rect);
        this.depth = depth;
        return this;
    }

    public int countParentValues() {
        int count = 0;
        if (parent != null) {
            count += parent.countValuesAndParents();
        }
        return count;
    }

    public int countValuesAndParents() {
        return valuesCount + countParentValues();
    }

    public int countSubValues() {
        int count = 0;
        if (isSplitted()) {
            Array<QuadTree> childArray = container.getTmpChildArray(this);
            for (QuadTree sub : childArray)
                count += sub.countValuesAndSubs();
            container.freeChildArray(childArray);
        }
        return count;
    }

    public int countValuesAndSubs() {
        return valuesCount + countSubValues();
    }

    public Array<Fixture<?>> getValuesAndSub(Array<Fixture<?>> fixtureArray) {
        for (int i = 0; i < valuesCount; i++) {
            fixtureArray.add(values[i]);
        }
        if (isSplitted()) {
            Array<QuadTree> childArray = container.getTmpChildArray(this);
            for (QuadTree sub : childArray)
                sub.getValuesAndSub(fixtureArray);
            container.freeChildArray(childArray);
        }
        return fixtureArray;
    }

    public int countSubQuad() {
        int count = 0;
        if (!isSplitted()) {
            return 0;
        } else {
            Array<QuadTree> childArray = container.getTmpChildArray(this);
            for (QuadTree sub : childArray)
                count += sub.isSplitted() ? sub.countSubQuad() : 1;
            container.freeChildArray(childArray);
        }
        return count;
    }


    public void regroup() {
        if (!isSplitted())
            return;
        Array<Fixture<?>> fixtureArrayValues = container.poolFixtureArray.obtain();
        getValuesAndSub(fixtureArrayValues);

        container.poolQuads.freeSub(this);
        for (Fixture f : fixtureArrayValues) {
            addValue(f);
        }
    }

    public void split() {
        if (isSplitted())
            return;
        container.poolQuads.initSubs(this);
        int newDepth = this.depth + 1;
        this.nw.init(this, QuadTreeUtils.getNW(boundingRect, nw.boundingRect), newDepth);
        this.sw.init(this, QuadTreeUtils.getSW(boundingRect, sw.boundingRect), newDepth);
        this.ne.init(this, QuadTreeUtils.getNE(boundingRect, ne.boundingRect), newDepth);
        this.se.init(this, QuadTreeUtils.getSE(boundingRect, se.boundingRect), newDepth);

        Array<Fixture<?>> arrayTmp = container.poolFixtureArray.obtain();
        arrayTmp.addAll(values,0,this.valuesCount);

        this.valuesCount = 0;
        ArrayUtils.clearValues(values);

        for (int i = 0, n = arrayTmp.size; i < n; i++) { //TODO
            addFixture(arrayTmp.get(i));
        }
    }

    public QuadTree getQuadTree(Vector2 pos) {
        return getQuadTree(pos.x, pos.y);
    }

    public QuadTree getQuadTree(float x, float y) {
        if (this.boundingRect.contains(x, y)) {
            if (!isSplitted())
                return this;
            Vector2 center = RectangleUtils.getCenter(boundingRect, container.tmp1);
            boolean xSup = x > center.x;
            boolean ySup = y > center.y;
            if (xSup) {
                if (ySup)
                    return ne.getQuadTree(x, y);
                else
                    return se.getQuadTree(x, y);
            } else if (ySup)
                return nw.getQuadTree(x, y);
            else
                return sw.getQuadTree(x, y);
        }
        return null;
    }

    public QuadTree getQuadTree(Rectangle rectangle) {
        if (RectangleUtils.containsStick(this.boundingRect, rectangle)) {
            if (!isSplitted())
                return this;
            QuadTree child = nw.getQuadTree(rectangle);
            if (child != null)
                return child;
            child = ne.getQuadTree(rectangle);
            if (child != null)
                return child;
            child = sw.getQuadTree(rectangle);
            if (child != null)
                return child;
            child = se.getQuadTree(rectangle);
            if (child != null)
                return child;
            return this;
        } else {
            return null;
        }
    }

    public boolean isSplitted() {
        return ne != null || nw != null || ne != null || nw != null;
    }


    public boolean shouldRegroup() {
        return isSplitted() && countValuesAndSubs() <= container.maxValues / 2;
    }


    public boolean shouldSplit() {
        if (isSplitted()
                || this.depth == container.maxDepth
                || valuesCount <= container.maxValues) {
            return false;
        }
        return valuesCount - QuadTreeUtils.countValuesCantSplitted(this) >= container.maxValues;
    }

    public void clearValues() {
        if (values.length > container.maxValues) {
            values = new Fixture[container.maxValues];
        } else {
            ArrayUtils.clearValues(values);
        }
        valuesCount = 0;
    }

    public void removeValue(Fixture fixture) {
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
        if (depth > 0 && parent.shouldRegroup()) {
            parent.regroup();
        }
    }

    public void addValue(Fixture fixture) {
        if (shouldSplit()) {
            split();
            addFixture(fixture);
        } else {
            if (valuesCount >= container.maxValues)
                values = Arrays.copyOf(values, valuesCount + container.maxValues);
            values[valuesCount++] = fixture;
            fixture.quadTree = this;
        }
    }

    public void removeFixture(Fixture fixture) {
        Rectangle rectangle = fixture.getBoundingRectangle();
        QuadTree quadTree = getQuadTree(rectangle);
        if (quadTree == null) {
            throw new GdxRuntimeException("Quad tree dont find fixture");
        } else {
            quadTree.removeValue(fixture);
        }
    }

    public void addFixture(Fixture fixture) {
        Rectangle rect = fixture.getBoundingRectangle();
        QuadTree quadTree = getQuadTree(rect);
        if (quadTree != null) {
            quadTree.addValue(fixture);
        } else {
            if (parent != null) {
                parent.addFixture(fixture);
            } else {
                container.growQuadTree(fixture);
            }
        }
    }
}
