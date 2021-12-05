package com.nzt.box.test.s_try.base.debugger;

import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;

public class ContactListenerDebugger implements ContactListener {

    public ContactListener contactListener;
    private WorldDebugger worldDebugger;

    public ContactListenerDebugger(WorldDebugger worldDebugger) {
        this.worldDebugger = worldDebugger;
    }

    @Override
    public void preSolve(ContactFixture contactFixture) {
        if (contactListener != null) {
            if (!worldDebugger.breakForFixture(contactFixture.fixtureA, BreakPoints.ContactPresolve))
                worldDebugger.breakForFixture(contactFixture.fixtureB, BreakPoints.ContactPresolve);
            contactListener.preSolve(contactFixture);
        }

    }

    @Override
    public void beginContact(ContactFixture contactFixture) {
        if (contactListener != null) {
            if (!worldDebugger.breakForFixture(contactFixture.fixtureA, BreakPoints.ContactBegin))
                worldDebugger.breakForFixture(contactFixture.fixtureB, BreakPoints.ContactBegin);
            contactListener.beginContact(contactFixture);
        }

    }

    @Override
    public void endContact(ContactFixture contactFixture) {
        if (contactListener != null) {
            if (!worldDebugger.breakForFixture(contactFixture.fixtureA, BreakPoints.ContactEnd))
                worldDebugger.breakForFixture(contactFixture.fixtureB, BreakPoints.ContactEnd);
            contactListener.endContact(contactFixture);
        }

    }

    @Override
    public void continueContact(ContactFixture contactFixture) {
        if (contactListener != null) {
            if (!worldDebugger.breakForFixture(contactFixture.fixtureA, BreakPoints.ContactContinue))
                worldDebugger.breakForFixture(contactFixture.fixtureB, BreakPoints.ContactContinue);
            contactListener.continueContact(contactFixture);
        }

    }
}
