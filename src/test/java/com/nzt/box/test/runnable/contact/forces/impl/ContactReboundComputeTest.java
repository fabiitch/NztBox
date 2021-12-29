package com.nzt.box.test.runnable.contact.forces.impl;

import com.nzt.box.bodies.BodyType;
import com.nzt.box.test.runnable.contact.forces.BaseComputeContactTest;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;
import org.junit.jupiter.api.Test;

public class ContactReboundComputeTest extends BaseComputeContactTest {


    public ContactReboundComputeTest(FastTesterMain main) {
        super(main);
    }

    @Test
    public void noTransfert() {
        bodyDef1.mass(1).restitution(0).transfert(0).receive(0);
        bodyDef2.mass(1).restitution(0).transfert(0).receive(0).bodyType(BodyType.Static);

        position1 = v(-200, 0);
        velocity1 = v(100, 0);

        position2 = v(200, 0);
        velocity2 = v(0, 0);

        data1.setVelExpected(v(0, 0));
        data2.setVelExpected(v(0, 0));
        startComputeContactTest();
    }

}
