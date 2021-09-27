package com.nzt.box.test.api.contact.forces.impl;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.test.api.contact.forces.BaseCalculForceTest;
import org.junit.jupiter.api.Test;

public class FrontalForceCalculTest extends BaseCalculForceTest {

    private Vector2 VEL_1 = v(100, 0);
    private Vector2 VEL_2 = v(-100, 0);

    @Test
    public void test1() {
        bodyDef1.mass(1).restitution(0).transfert(0).receive(0);
        bodyDef2.mass(1).restitution(0).transfert(0).receive(0);

        //pas de transfert donc rebound
        data1.setForceOn(v(-0, 0)).setVelocityAfter(v(-100, 0));
        data2.setForceOn(v(-0, 0)).setVelocityAfter(v(100, 0));
        testForceCompute();
    }

    @Test
    public void test2() {
        bodyDef1.mass(1).restitution(0).transfert(1).receive(1);
        bodyDef2.mass(1).restitution(0).transfert(1).receive(1);

        data1.setForceOn(v(-100, 0)).setVelocityAfter(v(-100, 0));
        data2.setForceOn(v(100, 0)).setVelocityAfter(v(100, 0));
        testForceCompute();
    }

    @Test
    public void test3() {
        bodyDef1.mass(1).restitution(1).transfert(1).receive(1);
        bodyDef2.mass(1).restitution(1).transfert(1).receive(1);

        data1.setForceOn(v(-0, 0)).setVelocityAfter(v(0, 0));
        data2.setForceOn(v(-0, 0)).setVelocityAfter(v(0, 0));
        testForceCompute();
    }

//    @Test
//    public void test4() {
//        bodyDef1.mass(10).restitution(0).transfert(1).receive(1);
//        bodyDef2.mass(1).restitution(0).transfert(1).receive(1);
//
//        data1.setForceOn(v(-0, 0)).setVelocityAfter(v(0, 0));
//        data2.setForceOn(v(-0, 0)).setVelocityAfter(v(0, 0));
//        testForceCompute();
//    }

    @Override
    protected Vector2 pos1() {
        return v(-200, 0);
    }

    @Override
    protected Vector2 pos2() {
        return v(200, 0);
    }

    @Override
    protected Vector2 vel1() {
        return VEL_1;
    }

    @Override
    protected Vector2 vel2() {
        return VEL_2;
    }
}
