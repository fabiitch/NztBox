package com.nzt.box.test.runnable.contact.forces.impl;

import com.nzt.box.test.runnable.contact.forces.BaseComputeContactTest;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactComputeFrontalTest extends BaseComputeContactTest {


    public ContactComputeFrontalTest(FastTesterMain main) {
        super(main);
    }

    @BeforeEach
    public void init() {
        position1 = v(-200, 0);
        velocity1 = v(100, 0);

        position2 = v(200, 0);
        velocity2 = v(-100, 0);

    }

    @Test
    public void noTransfert() {
        bodyDef1.mass(1).restitution(0).transfert(0).receive(0);
        bodyDef2.mass(1).restitution(0).transfert(0).receive(0);

        data1.setVelExpected(v(0, 0));
        data2.setVelExpected(v(0, 0));
        startComputeContactTest();
    }

    @Test
    public void normal() {
        bodyDef1.mass(1).restitution(0).transfert(1).receive(1);
        bodyDef2.mass(1).restitution(0).transfert(1).receive(1);

        data1.setVelExpected(v(-100, 0));
        data2.setVelExpected(v(100, 0));
        startComputeContactTest();
    }

    @Test
    public void test3() {
        velocity1 = v(50, 0);
        bodyDef1.mass(1).restitution(1).transfert(1).receive(1);
        bodyDef2.mass(1).restitution(1).transfert(1).receive(1);

        data1.setVelExpected(v(-100, 0));
        data2.setVelExpected(v(50, 0));
        startComputeContactTest();
    }

}
