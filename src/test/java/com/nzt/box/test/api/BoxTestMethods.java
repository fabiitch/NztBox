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
        VTestUtils.assertEquals(data1.forceOnA, data2.forceOnA, "forceOnA not equals");
        VTestUtils.assertEquals(data1.forceOnB, data2.forceOnB, "forceOnB not equals");
        VTestUtils.assertEquals(data1.reboundA, data2.reboundA, "reboundA not equals");
        VTestUtils.assertEquals(data1.reboundB, data2.reboundB, "reboundB not equals");
    }

}
