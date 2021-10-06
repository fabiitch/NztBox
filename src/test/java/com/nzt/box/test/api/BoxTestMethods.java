package com.nzt.box.test.api;

import com.badlogic.gdx.math.MathUtils;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.gdx.test.api.math.vectors.VTestUtils;

public class BoxTestMethods {

    private static final float TOLERANCE = MathUtils.FLOAT_ROUNDING_ERROR;

    public static void equals(CollisionData data1, CollisionData data2) {
        if (data1 == data2)
            return;
        VTestUtils.assertEquals(data1.normal, data2.normal, "normal not equals");
        VTestUtils.assertEquals(data1.newVelA, data2.newVelA, "newVelA not equals");
        VTestUtils.assertEquals(data1.newVelB, data2.newVelB, "newVelB not equals");
    }

}
