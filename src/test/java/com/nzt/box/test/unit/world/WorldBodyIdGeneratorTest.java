package com.nzt.box.test.unit.world;

import com.nzt.box.test.mock.BodyMock;
import com.nzt.box.world.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WorldBodyIdGeneratorTest {

    @Test
    public void bodyIdGeneratorResetTest() {
        World world = new World();
        world.data.bodiesIdGenerator = Integer.MAX_VALUE - 9;
        for (int i = 0; i < 10; i++) {
            world.addBody(new BodyMock());
        }
        Assertions.assertEquals(2, world.data.bodiesIdGenerator);
    }
}
