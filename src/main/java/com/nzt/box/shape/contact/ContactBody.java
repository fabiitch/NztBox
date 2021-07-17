package com.nzt.box.shape.contact;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.nzt.box.bodies.Fixture;

public class ContactBody implements Pool.Poolable {

    public Fixture fixtureA;
    public Fixture fixtureB;
    public boolean tickEveryStep = false;

    public ContactBody() {
    }

    public boolean retry() {
        return fixtureA.testContact(fixtureB);
    }

    public boolean isThis(Fixture fixtureA, Fixture fixtureB) {
        if (this.fixtureA == fixtureA || this.fixtureA == fixtureB) {
            if (this.fixtureB == fixtureA || this.fixtureB == fixtureB)
                return true;
        }
        return false;
    }

    public static ContactBody get(Fixture fixtureA, Fixture fixtureB) {
        ContactBody contactBody = Pools.obtain(ContactBody.class);
        contactBody.fixtureA = fixtureA;
        contactBody.fixtureB = fixtureB;
        return contactBody;
    }

    @Override
    public void reset() {
        System.out.println("Called");
        fixtureA = null;
        fixtureB = null;
        tickEveryStep = false;
    }
}
