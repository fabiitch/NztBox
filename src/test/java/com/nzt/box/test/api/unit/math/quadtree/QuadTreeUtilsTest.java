package com.nzt.box.test.api.unit.math.quadtree;

import com.badlogic.gdx.math.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.nzt.gdx.test.api.math.AbstractMathTest.r;
import static com.nzt.box.math.quadtree.QuadTreeUtils.*;

public class QuadTreeUtilsTest {

    @Test
    public void getSubRegionRect() {
        Rectangle rect = new Rectangle(0, 0, 200, 100);
        Assertions.assertEquals(getNW(rect, r()), r(0, 50, 100, 50));
        Assertions.assertEquals(getSW(rect, r()), r(0, 0, 100, 50));
        Assertions.assertEquals(getNE(rect, r()), r(100, 50, 100, 50));
        Assertions.assertEquals(getSE(rect, r()), r(100, 0, 100, 50));
    }

    @Test
    public void splitContainsRectTest() {
        Rectangle parent = new Rectangle(0, 0, 200, 100);

        Assertions.assertTrue(splitContainsRect(parent, r(0,0,100,50)));
        Assertions.assertTrue(splitContainsRect(parent, r(100,50,100,50)));
        Assertions.assertFalse(splitContainsRect(parent, r(100,50,100.001f,50)));

        Assertions.assertTrue(splitContainsRect(parent, r(80,40,20,10)));
        Assertions.assertFalse(splitContainsRect(parent, r(81,41,20,10)));

        Assertions.assertFalse(splitContainsRect(parent, r(90,10,20,10)));
    }
}
