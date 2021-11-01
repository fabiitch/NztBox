package com.nzt.box.test.api.unit.math;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeContainer;
import com.nzt.box.test.api.mock.FixtureMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuadTreeTest {


    @Test
    public void getRegionTest() {
        QuadTreeContainer container = new QuadTreeContainer();
        QuadTree quadTree = new QuadTree(container);
        quadTree.boundingRect = new Rectangle(0, 0, 100, 100);

        Assertions.assertEquals(null, quadTree.getQuadTree(-10, 10));
        Assertions.assertEquals(quadTree, quadTree.getQuadTree(10, 10));

        quadTree.nw = new QuadTree(container);
        quadTree.nw.boundingRect.set(0, 50, 50, 50);

        quadTree.ne = new QuadTree(container);
        quadTree.ne.boundingRect.set(50, 50, 50, 50);

        quadTree.sw = new QuadTree(container);
        quadTree.sw.boundingRect.set(0, 0, 50, 50);

        quadTree.se = new QuadTree(container);
        quadTree.se.boundingRect.set(50, 0, 50, 50);

        Assertions.assertEquals(null, quadTree.getQuadTree(-10, 10));

        Assertions.assertEquals(quadTree.sw, quadTree.getQuadTree(10, 10));
        Assertions.assertEquals(quadTree.se, quadTree.getQuadTree(60, 10));

        Assertions.assertEquals(quadTree.nw, quadTree.getQuadTree(10, 60));
        Assertions.assertEquals(quadTree.ne, quadTree.getQuadTree(60, 60));

    }


    @Test
    public void getSubQuadCountTest() {
        QuadTreeContainer quadTreeContainer = new QuadTreeContainer(new Rectangle(), 10, 10);
        QuadTree quadTreeRoot = new QuadTree(quadTreeContainer);
        Assertions.assertEquals(0, quadTreeRoot.countSubQuad());

        quadTreeRoot.split();
        Assertions.assertEquals(4, quadTreeRoot.countSubQuad());

        Array<QuadTree> childArray = quadTreeContainer.getTmpChildArray(quadTreeRoot);
        for (QuadTree quadTree : childArray) {
            quadTree.split();
        }
        Assertions.assertEquals(16, quadTreeRoot.countSubQuad());
        Assertions.assertEquals(4, quadTreeRoot.nw.countSubQuad());

        quadTreeRoot.nw.nw.split();
        Assertions.assertEquals(19, quadTreeRoot.countSubQuad());
        Assertions.assertEquals(7, quadTreeRoot.nw.countSubQuad());
        Assertions.assertEquals(4, quadTreeRoot.nw.nw.countSubQuad());


        quadTreeRoot.nw.nw.nw.split();
        Assertions.assertEquals(22, quadTreeRoot.countSubQuad());
        Assertions.assertEquals(10, quadTreeRoot.nw.countSubQuad());
        Assertions.assertEquals(7, quadTreeRoot.nw.nw.countSubQuad());
        Assertions.assertEquals(4, quadTreeRoot.nw.nw.nw.countSubQuad());
    }

    private void addFixtureInQuad(QuadTree quadTree, int nbAdd) {
        for (int i = 0; i < nbAdd; i++) {
            quadTree.addValue(new FixtureMock());
        }
    }

    @Test
    public void countSubValuesTest1() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        Assertions.assertEquals(0, quadTreeRoot.countSubValues(false));
        addFixtureInQuad(quadTreeRoot, 4);
        Assertions.assertEquals(4, quadTreeRoot.countSubValues(false));
    }

    @Test
    public void countSubValuesTest2() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        Assertions.assertEquals(0, quadTreeRoot.countSubValues(false));
        quadTreeRoot.split();
        addFixtureInQuad(quadTreeRoot.ne, 4);
        addFixtureInQuad(quadTreeRoot.sw, 4);
        Assertions.assertEquals(8, quadTreeRoot.countSubValues(false));
        Assertions.assertEquals(4, quadTreeRoot.ne.countSubValues(false));
        Assertions.assertEquals(4, quadTreeRoot.sw.countSubValues(false));
    }

    @Test
    public void countSubValuesTest3() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        quadTreeRoot.split();
        addFixtureInQuad(quadTreeRoot.nw, 4);
        quadTreeRoot.ne.split();
        addFixtureInQuad(quadTreeRoot.ne.ne, 4);
        addFixtureInQuad(quadTreeRoot.ne.nw, 4);
        Assertions.assertEquals(12, quadTreeRoot.countSubValues(false));
        Assertions.assertEquals(4, quadTreeRoot.ne.ne.countSubValues(false));
    }
}
