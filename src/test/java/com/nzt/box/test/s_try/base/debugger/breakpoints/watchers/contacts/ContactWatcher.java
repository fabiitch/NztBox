package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoint;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;

public class ContactWatcher implements BreakPoint {
    public boolean breakAtPresolve = false;
    public boolean breakAtBegin = true;
    public boolean breakAtEnd = true;
    public boolean breakAtContinue= false;

    public ContactWatcher() {
    }

    public ContactWatcher(boolean breakAtBegin, boolean breakAtEnd, boolean breakAtContinue) {
        this.breakAtBegin = breakAtBegin;
        this.breakAtEnd = breakAtEnd;
        this.breakAtContinue = breakAtContinue;
    }

    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return false;
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return false;
    }

    @Override
    public boolean breakForContact(Fixture fixtureA, Fixture fixtureB, BreakPoints action) {
        switch (action) {
            case ContactPresolve:
                return breakAtPresolve;
            case ContactBegin:
                return breakAtBegin;
            case ContactContinue:
                return breakAtContinue;
            case ContactEnd:
                return breakAtEnd;
        }
        return false;
    }
}
