package com.nzt.box.test.api.unit.world;

import com.nzt.box.test.api.mock.BodyMock;
import com.nzt.box.world.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldHelperTest {

    @Test
    public void bodyIdGeneratorResetTest() {
        World world = new World();
        world.helper.bodiesIdGenerator = Integer.MAX_VALUE - 9;
        for (int i = 0; i < 10; i++) {
            world.addBody(new BodyMock());
        }
        Assertions.assertEquals(2, world.helper.bodiesIdGenerator);
    }
}
