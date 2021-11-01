package com.nzt.box.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Fixture;
import com.nzt.gdx.math.shapes.utils.RectangleUtils;
import com.nzt.gdx.utils.ArrayUtils;
import com.nzt.gdx.utils.GdxArrayUtils;

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
            container.quadPool.free(ne);
            this.ne = null;
        }
        if (nw != null) {
            container.quadPool.free(nw);
            this.nw = null;
        }
        if (se != null) {
            container.quadPool.free(se);
            this.se = null;
        }

        if (sw != null) {
            container.quadPool.free(sw);
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

    public int countSubValues(boolean allowDuplicate) {
        int count;
        if (allowDuplicate)
            count = countSubValuesWithDuplicate();
        else {
            Array<Fixture<?>> array = container.fixtureArrayPool.obtain();
            count = getSubValues(array, false).size;
            container.fixtureArrayPool.free(array);
        }
        return count;
    }

    private int countSubValuesWithDuplicate() {
        int count = 0;
        if (!isSplitted()) {
            return valuesCount;
        } else {
            Array<QuadTree> childArray = container.getTmpChildArray(this);
            for (QuadTree sub : childArray)
                count += sub.countSubValuesWithDuplicate();
            container.freeChildArray(childArray);
        }
        return count;
    }

    public Array<Fixture<?>> getSubValues(Array<Fixture<?>> fixtureArray, boolean allowDuplicate) {
        if (!isSplitted()) {
            for (int i = 0; i < valuesCount; i++) {
                if (allowDuplicate)
                    fixtureArray.add(values[i]);
                else
                    GdxArrayUtils.addIfNotPresent(fixtureArray, values[i]);
            }
        } else {
            Array<QuadTree> childArray = container.getTmpChildArray(this);
            for (QuadTree sub : childArray)
                sub.getSubValues(fixtureArray, allowDuplicate);
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

        Array<Fixture<?>> fixtureArrayValues = container.fixtureArrayPool.obtain();
        getSubValues(fixtureArrayValues, false);
        for (Fixture f : fixtureArrayValues) {
            addValue(f);
        }
        container.quadPool.free(ne);
        container.quadPool.free(nw);
        container.quadPool.free(se);
        container.quadPool.free(sw);
        this.ne = this.nw= this.se = this.sw = null;
//        container.quadPool.free(ne);

//        ne.regroup();
//        for (Fixture<?> value : ne.values) {
//            if (value != null)
//                addValue(value);
//        }
//        container.quadPool.free(ne);
//        this.ne = null;
//
//        nw.regroup();
//        for (Fixture<?> value : nw.values) {
//            if (value != null)
//                addValue(value);
//        }
//        container.quadPool.free(nw);
//        this.nw = null;
//
//        sw.regroup();
//        for (Fixture<?> value : sw.values) {
//            if (value != null)
//                addValue(value);
//        }
//        container.quadPool.free(sw);
//        this.sw = null;
//
//        se.regroup();
//        for (Fixture<?> value : se.values) {
//            if (value != null)
//                addValue(value);
//        }
//        container.quadPool.free(se);
//        this.se = null;

    }

    public void split() {
        if (isSplitted())
            return;
        float halfWitdh = boundingRect.width / 2;
        float halfHeight = boundingRect.height / 2;
        Rectangle rectSW = new Rectangle(boundingRect.x, boundingRect.y, halfWitdh, halfHeight);
        Rectangle rectSE = new Rectangle(boundingRect.x + halfWitdh, boundingRect.y, halfWitdh, halfHeight);
        Rectangle rectNW = new Rectangle(boundingRect.x, boundingRect.y + halfHeight, halfWitdh, halfHeight);
        Rectangle rectNE = new Rectangle(boundingRect.x + halfWitdh, boundingRect.y + halfHeight, halfWitdh, halfHeight);

        final QuadTree SW = container.quadPool.obtain();
        this.sw = SW.init(this, rectSW, this.depth + 1);

        final QuadTree SE = container.quadPool.obtain();
        this.se = SE.init(this, rectSE, this.depth + 1);

        final QuadTree NW = container.quadPool.obtain();
        this.nw = NW.init(this, rectNW, this.depth + 1);

        final QuadTree NE = container.quadPool.obtain();
        this.ne = NE.init(this, rectNE, this.depth + 1);

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

    private Array<QuadTree> getQuadsTree(Rectangle rectangle) {
        Array<QuadTree> tmpQuadArray = container.quadArrayPool.obtain();

        QuadTree quadTreeToAdd = getQuadTree(RectangleUtils.getA(rectangle, container.tmp1));
        if (quadTreeToAdd != null){
            tmpQuadArray.add(quadTreeToAdd);
            if(quadTreeToAdd.boundingRect.contains(rectangle)) //contien les autres point
                return tmpQuadArray;
        }

        quadTreeToAdd = getQuadTree(RectangleUtils.getB(rectangle, container.tmp1));
        if (quadTreeToAdd != null)
            if (!tmpQuadArray.contains(quadTreeToAdd, true))
                tmpQuadArray.add(quadTreeToAdd);

        quadTreeToAdd = getQuadTree(RectangleUtils.getC(rectangle, container.tmp1));
        if (quadTreeToAdd != null)
            if (!tmpQuadArray.contains(quadTreeToAdd, true))
                tmpQuadArray.add(quadTreeToAdd);

        quadTreeToAdd = getQuadTree(RectangleUtils.getD(rectangle, container.tmp1));
        if (quadTreeToAdd != null)
            if (!tmpQuadArray.contains(quadTreeToAdd, true))
                tmpQuadArray.add(quadTreeToAdd);
        return tmpQuadArray;
    }

    public boolean isSplitted() {
        return ne != null || nw != null || ne != null || nw != null;
    }


    public boolean shouldRegroup() {
        return isSplitted() && countSubValuesWithDuplicate() <= container.maxValues / 2;
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
        if (valuesCount + 1 > container.maxValues && this.depth < container.maxDepth) {
            split();
            addFixture(fixture);
        } else {
            if (valuesCount >= container.maxValues)
                values = Arrays.copyOf(values, valuesCount + container.maxValues);
            values[valuesCount++] = fixture;
        }
    }

    public void removeFixture(Fixture fixture) {
        Rectangle rectangle = fixture.getBoundingRectangle();
        Array<QuadTree> tmpQuadArray = getQuadsTree(rectangle);
        for (QuadTree quadTree : tmpQuadArray) {
            quadTree.removeValue(fixture);
        }
    }

    public void addFixture(Fixture fixture) {
        Rectangle rect = fixture.getBoundingRectangle();
        Array<QuadTree> tmpQuadArray = getQuadsTree(rect);
        if (!tmpQuadArray.isEmpty()) {
            for (QuadTree quadTree : tmpQuadArray) {
                quadTree.addValue(fixture);
            }
        } else {
            if (parent != null) {
                parent.addFixture(fixture);
            } else {
                container.growQuadTree(fixture);
            }
        }
        container.quadArrayPool.free(tmpQuadArray);
    }


}
