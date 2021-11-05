package com.nzt.box.test.api.unit.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeContainer;
import com.nzt.box.test.api.mock.FixtureMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuadTreeCountTest {

    private void addFixtureInQuad(QuadTree quadTree, int nbAdd) {
        for (int i = 0; i < nbAdd; i++) {
            quadTree.addValue(new FixtureMock());
        }
    }

    @Test
    public void countSubValuesTest() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 200, 200);
        QuadTree quadTreeRoot = container.root;
        Assertions.assertEquals(0, quadTreeRoot.countValuesAndSubs());
        addFixtureInQuad(quadTreeRoot, 4);
        Assertions.assertEquals(4, quadTreeRoot.countValuesAndSubs());
    }

    @Test
    public void countSubValuesTest2() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        quadTreeRoot.split();
        addFixtureInQuad(quadTreeRoot, 4);
        addFixtureInQuad(quadTreeRoot.ne, 4);
        addFixtureInQuad(quadTreeRoot.sw, 4);
        Assertions.assertEquals(12, quadTreeRoot.countValuesAndSubs());
        Assertions.assertEquals(4, quadTreeRoot.ne.countValuesAndSubs());
        Assertions.assertEquals(4, quadTreeRoot.sw.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.se.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.nw.countValuesAndSubs());
    }

    @Test
    public void countSubValuesTest3() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        quadTreeRoot.split();//1
        quadTreeRoot.ne.split();//2
        quadTreeRoot.sw.split();//2
        quadTreeRoot.sw.sw.split();//3
        addFixtureInQuad(quadTreeRoot, 4);

        addFixtureInQuad(quadTreeRoot.ne, 4);
        addFixtureInQuad(quadTreeRoot.sw, 4);

        addFixtureInQuad(quadTreeRoot.ne.ne, 4);
        addFixtureInQuad(quadTreeRoot.ne.se, 4);
        addFixtureInQuad(quadTreeRoot.ne.sw, 1);

        addFixtureInQuad(quadTreeRoot.sw.ne, 5);
        addFixtureInQuad(quadTreeRoot.sw.nw, 5);
        addFixtureInQuad(quadTreeRoot.sw.ne, 5);

        addFixtureInQuad(quadTreeRoot.sw.sw.sw, 10);
        addFixtureInQuad(quadTreeRoot.sw.sw.ne, 10);

        Assertions.assertEquals(56, quadTreeRoot.countValuesAndSubs());

        Assertions.assertEquals(13, quadTreeRoot.ne.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.nw.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.se.countValuesAndSubs());
        Assertions.assertEquals(39, quadTreeRoot.sw.countValuesAndSubs());

        Assertions.assertEquals(4, quadTreeRoot.ne.ne.countValuesAndSubs());
        Assertions.assertEquals(4, quadTreeRoot.ne.se.countValuesAndSubs());
        Assertions.assertEquals(1, quadTreeRoot.ne.sw.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.ne.nw.countValuesAndSubs());

        Assertions.assertEquals(10, quadTreeRoot.sw.ne.countValuesAndSubs());
        Assertions.assertEquals(5, quadTreeRoot.sw.nw.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.sw.se.countValuesAndSubs());
        Assertions.assertEquals(20, quadTreeRoot.sw.sw.countValuesAndSubs());

        Assertions.assertEquals(10, quadTreeRoot.sw.sw.sw.countValuesAndSubs());
        Assertions.assertEquals(10, quadTreeRoot.sw.sw.ne.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.sw.sw.se.countValuesAndSubs());
        Assertions.assertEquals(0, quadTreeRoot.sw.sw.nw.countValuesAndSubs());
    }


    @Test
    public void countParentValuesTest() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 200, 200);
        QuadTree quadTreeRoot = container.root;
        Assertions.assertEquals(0, quadTreeRoot.countValuesAndParents());
        addFixtureInQuad(quadTreeRoot, 4);
        Assertions.assertEquals(4, quadTreeRoot.countValuesAndParents());
    }

    @Test
    public void countParentValuesTest2() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        quadTreeRoot.split();
        addFixtureInQuad(quadTreeRoot, 4);
        addFixtureInQuad(quadTreeRoot.ne, 4);
        addFixtureInQuad(quadTreeRoot.sw, 4);
        Assertions.assertEquals(4, quadTreeRoot.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.ne.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.sw.countValuesAndParents());
        Assertions.assertEquals(4, quadTreeRoot.se.countValuesAndParents());
        Assertions.assertEquals(4, quadTreeRoot.nw.countValuesAndParents());
    }

    @Test
    public void countParentValuesTest3() {
        QuadTreeContainer container = new QuadTreeContainer(new Rectangle(0, 0, 1000, 500), 50, 50);
        QuadTree quadTreeRoot = container.root;
        quadTreeRoot.split();//1
        quadTreeRoot.ne.split();//2
        quadTreeRoot.sw.split();//2
        quadTreeRoot.sw.sw.split();//3
        addFixtureInQuad(quadTreeRoot, 4);

        addFixtureInQuad(quadTreeRoot.ne, 4);
        addFixtureInQuad(quadTreeRoot.sw, 4);

        addFixtureInQuad(quadTreeRoot.ne.ne, 4);
        addFixtureInQuad(quadTreeRoot.ne.se, 4);
        addFixtureInQuad(quadTreeRoot.ne.sw, 1);

        addFixtureInQuad(quadTreeRoot.sw.ne, 5);
        addFixtureInQuad(quadTreeRoot.sw.ne, 5);
        addFixtureInQuad(quadTreeRoot.sw.nw, 5);

        addFixtureInQuad(quadTreeRoot.sw.sw.sw, 10);
        addFixtureInQuad(quadTreeRoot.sw.sw.ne, 10);

        Assertions.assertEquals(4, quadTreeRoot.countValuesAndParents());

        Assertions.assertEquals(8, quadTreeRoot.ne.countValuesAndParents());
        Assertions.assertEquals(4, quadTreeRoot.nw.countValuesAndParents());
        Assertions.assertEquals(4, quadTreeRoot.se.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.sw.countValuesAndParents());

        Assertions.assertEquals(12, quadTreeRoot.ne.ne.countValuesAndParents());
        Assertions.assertEquals(12, quadTreeRoot.ne.se.countValuesAndParents());
        Assertions.assertEquals(9, quadTreeRoot.ne.sw.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.ne.nw.countValuesAndParents());

        Assertions.assertEquals(18, quadTreeRoot.sw.ne.countValuesAndParents());
        Assertions.assertEquals(13, quadTreeRoot.sw.nw.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.sw.se.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.sw.sw.countValuesAndParents());

        Assertions.assertEquals(18, quadTreeRoot.sw.sw.sw.countValuesAndParents());
        Assertions.assertEquals(18, quadTreeRoot.sw.sw.ne.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.sw.sw.se.countValuesAndParents());
        Assertions.assertEquals(8, quadTreeRoot.sw.sw.nw.countValuesAndParents());
    }

}
