package com.nzt.box.test.api.contact.forces.impl;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.test.api.contact.forces.BaseCalculForceTest;
import org.junit.jupiter.api.Test;

public class FrontalForceCalculTest extends BaseCalculForceTest {

    @Test
    public void test1() {
        bodyDef1.mass(1).restitution(0).transfert(0).receive(1);
        bodyDef2.mass(1).restitution(0).transfert(0).receive(1);

        data1.set(v(-100, 0), v(0, 0));
        data2.set(v(100, 0), v(0, 0));
        testForceCompute();
    }


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
        return v(100, 0);
    }

    @Override
    protected Vector2 vel2() {
        return v(-100, 0);
    }
}
