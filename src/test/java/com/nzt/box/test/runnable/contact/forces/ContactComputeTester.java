package com.nzt.box.test.runnable.contact.forces;

import com.nzt.box.contact.compute.ContactCompute;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.test.unit.math.vectors.VTestUtils;

class ContactComputeTester extends ContactCompute {
    public float tolerance = 0.1f;
    public ForceDataResult dataBodyA, dataBodyB;
    public boolean computeDone;

    public ContactComputeTester(ForceDataResult dataBodyA, ForceDataResult dataBodyB) {
        this.dataBodyA = dataBodyA;
        this.dataBodyB = dataBodyB;
    }

    @Override
    public void computeContact(ContactFixture contactFixture) {
        super.computeContact(contactFixture);
        CollisionData collisionData = contactFixture.collisionData;
        if (contactFixture.imBodyA(dataBodyA.body)) {
            VTestUtils.assertEquals(dataBodyA.velExpected, collisionData.newVelA, tolerance, "Velocity Body A after");
            VTestUtils.assertEquals(dataBodyB.velExpected, collisionData.newVelB, tolerance, "Velocity Body B after");
        } else {
            VTestUtils.assertEquals(dataBodyB.velExpected, collisionData.newVelA, tolerance, "Velocity Body B after");
            VTestUtils.assertEquals(dataBodyA.velExpected, collisionData.newVelB, tolerance, "Velocity Body A after");
        }
        computeDone = true;
    }
}

