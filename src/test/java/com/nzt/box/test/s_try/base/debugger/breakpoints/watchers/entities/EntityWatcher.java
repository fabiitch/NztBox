package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.entities;

import com.badlogic.gdx.utils.IdentityMap;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoint;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;
import com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts.ContactWatcher;

abstract class EntityWatcher<T> implements BreakPoint {
    public T entity;

    public boolean breakAtMove, breakAtBodyCheckCollision, breakAtFixtureCheckCollision;
    public ContactWatcher contactWatcher = new ContactWatcher();

    public IdentityMap<Body, ContactWatcher> bodiesContactWatchers = new IdentityMap<>();
    public IdentityMap<Fixture, ContactWatcher> fixtureContactWatchers = new IdentityMap<>();

    public EntityWatcher(T entity) {
        this.entity = entity;
    }

    protected boolean breakForAction(BreakPoints action) {
        switch (action) {
            case BodyMove:
                return breakAtMove;
            case BodyCheckCollision:
                return breakAtBodyCheckCollision;
            case FixtureCheckCollision:
                return breakAtFixtureCheckCollision;
        }
        return false;
    }


}
