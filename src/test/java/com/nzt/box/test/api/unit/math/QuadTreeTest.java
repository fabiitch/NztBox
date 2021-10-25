package com.nzt.box.test.api.unit.math;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeHelper;
import com.nzt.box.test.api.mock.BodyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class QuadTreeTest {


    @Test
    public void getRegionTest() {
        QuadTree quadTree = new QuadTree();
        quadTree.boundingRect = new Rectangle(0, 0, 100, 100);

        Assertions.assertEquals(null, quadTree.getQuadTree(-10, 10));
        Assertions.assertEquals(quadTree, quadTree.getQuadTree(10, 10));

        quadTree.nw = new QuadTree();
        quadTree.nw.boundingRect.set(0, 50, 50, 50);

        quadTree.ne = new QuadTree();
        quadTree.ne.boundingRect.set(50, 50, 50, 50);

        quadTree.sw = new QuadTree();
        quadTree.sw.boundingRect.set(0, 0, 50, 50);

        quadTree.se = new QuadTree();
        quadTree.se.boundingRect.set(50, 0, 50, 50);

        Assertions.assertEquals(null, quadTree.getQuadTree(-10, 10));

        Assertions.assertEquals(quadTree.sw, quadTree.getQuadTree(10, 10));
        Assertions.assertEquals(quadTree.se, quadTree.getQuadTree(60, 10));

        Assertions.assertEquals(quadTree.nw, quadTree.getQuadTree(10, 60));
        Assertions.assertEquals(quadTree.ne, quadTree.getQuadTree(60, 60));

    }


    @Test
    public void getSubQuadCountTest() {
        QuadTree quadTreeRoot = new QuadTree();
        quadTreeRoot.init(new QuadTreeHelper(quadTreeRoot), new Rectangle(), 10, 10);
        Assertions.assertEquals(0, quadTreeRoot.countSubQuad());

        quadTreeRoot.split();
        Assertions.assertEquals(4, quadTreeRoot.countSubQuad());

        Array<QuadTree> childArray = quadTreeRoot.helper.getChildArray(quadTreeRoot);
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

    @Test
    public void countSubValues() {
         int countuserData = 1;
        BiConsumer<QuadTree, Integer> funcAdd = new BiConsumer<QuadTree, Integer>() {
            @Override
            public void accept(QuadTree quadTree, Integer count) {
                for (int i = 0; i < count; i++)
                    quadTree.addBody(new BodyMock(countuserData+""));
            }
        };

        QuadTree quadTreeRoot = new QuadTree();
        quadTreeRoot.init(new QuadTreeHelper(quadTreeRoot), new Rectangle(0, 0, 1000, 500), 10, 10);
        Assertions.assertEquals(0, quadTreeRoot.countSubValues());

        funcAdd.accept(quadTreeRoot, 4);
        Assertions.assertEquals(4, quadTreeRoot.countSubValues());

        quadTreeRoot.split();
        funcAdd.accept(quadTreeRoot.ne, 4);
        funcAdd.accept(quadTreeRoot.sw, 4);
        Assertions.assertEquals(12, quadTreeRoot.countSubValues());
        Assertions.assertEquals(4, quadTreeRoot.ne.countSubValues());
        Assertions.assertEquals(4, quadTreeRoot.sw.countSubValues());

        quadTreeRoot.ne.split();
        funcAdd.accept(quadTreeRoot.ne.ne, 4);
        Assertions.assertEquals(16, quadTreeRoot.countSubValues());
        Assertions.assertEquals(4, quadTreeRoot.ne.ne.countSubValues());
    }


}
