package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoint;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;

abstract class EntityContactWatcher<T> implements BreakPoint {

    public T entityA, entityB;
    public ContactWatcher contactWatcher;

    public EntityContactWatcher(T entityA, T entityB) {
        this.entityA = entityA;
        this.entityB = entityB;
    }

    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return false;
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return false;
    }

}
