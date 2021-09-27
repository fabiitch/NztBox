package com.nzt.box.test.api.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.contact.compute.ContactForces;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.test.api.math.vectors.VTestUtils;

class ContactForcesTester extends ContactForces {
    public float tolerance = 0.1f;
    public ForceDataResult data1, data2;
    public boolean computeDone;

    public ContactForcesTester(ForceDataResult data1, ForceDataResult data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    public void applyForces(ContactFixture contactFixture) {
        if (contactFixture.imBodyA(data1.body)) {
            VTestUtils.assertEquals(data1.forceOn, contactFixture.collisionData.forceOnA, tolerance, "ForceOnA");
            VTestUtils.assertEquals(data2.forceOn, contactFixture.collisionData.forceOnB, tolerance, "ForceOnB");
        } else {
            VTestUtils.assertEquals(data2.forceOn, contactFixture.collisionData.forceOnA, tolerance, "ForceOnA");
            VTestUtils.assertEquals(data1.forceOn, contactFixture.collisionData.forceOnB, tolerance, "ForceOnB");
        }

        super.applyForces(contactFixture);

        if (contactFixture.imBodyA(data1.body)) {
            VTestUtils.assertEquals(data1.velocityAfter, data1.body.getVelocity(new Vector2()), tolerance, "Vel A after");
            VTestUtils.assertEquals(data2.velocityAfter, data2.body.getVelocity(new Vector2()), tolerance, "Vel B after");
        } else {
            VTestUtils.assertEquals(data2.velocityAfter, data2.body.getVelocity(new Vector2()), tolerance, "Vel A after");
            VTestUtils.assertEquals(data1.velocityAfter, data1.body.getVelocity(new Vector2()), tolerance, "Vel B after");
        }
        computeDone = true;
    }
}

