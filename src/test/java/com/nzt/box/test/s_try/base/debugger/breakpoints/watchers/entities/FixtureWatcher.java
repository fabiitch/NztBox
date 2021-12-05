package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.entities;

import com.badlogic.gdx.utils.ObjectMap;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;
import com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts.ContactWatcher;

public class FixtureWatcher extends EntityWatcher<Fixture> {

    public FixtureWatcher(Fixture entity) {
        super(entity);
    }

    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return this.entity.body == body;
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return this.entity == fixture;
    }

    @Override
    public boolean breakForContact(Fixture fixtureA, Fixture fixtureB, BreakPoints action) {
        if (breakForFixture(fixtureA, action) || breakForFixture(fixtureB, action)) {
            if (contactWatcher.breakForContact(fixtureA, fixtureB, action))
                return true;

            Body otherBody = this.entity.body == fixtureA.body ? fixtureB.body : fixtureA.body;
            for (ObjectMap.Entry<Body, ContactWatcher> entry : bodiesContactWatchers) {
                if (otherBody == entry.key && entry.value.breakForContact(fixtureA, fixtureB, action))
                    return true;
            }

            Fixture otherFixture = this.entity == fixtureA ? fixtureB : fixtureA;
            for (ObjectMap.Entry<Fixture, ContactWatcher> entry : fixtureContactWatchers) {
                if (otherFixture == entry.key && entry.value.breakForContact(fixtureA, fixtureB, action))
                    return true;
            }
        }
        return false;
    }
}
