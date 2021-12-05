package com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.entities;

import com.badlogic.gdx.utils.ObjectMap;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.test.s_try.base.debugger.breakpoints.BreakPoints;
import com.nzt.box.test.s_try.base.debugger.breakpoints.watchers.contacts.ContactWatcher;

public class BodyWatcher extends EntityWatcher<Body> {

    public BodyWatcher(Body entity) {
        super(entity);
    }

    @Override
    public boolean breakForBody(Body body, BreakPoints action) {
        return this.entity == body && breakForAction(action);
    }

    @Override
    public boolean breakForFixture(Fixture fixture, BreakPoints action) {
        return this.entity.fixtures.contains(fixture, true) && breakForAction(action);
    }

    @Override
    public boolean breakForContact(Fixture fixtureA, Fixture fixtureB, BreakPoints action) {
        if (this.entity.fixtures.contains(fixtureA, true) || this.entity.fixtures.contains(fixtureB, true)) {
            if (contactWatcher.breakForContact(fixtureA, fixtureB, action))
                return true;

            Body otherBody = entity == fixtureA.body ? fixtureB.body : fixtureA.body;
            for (ObjectMap.Entry<Body, ContactWatcher> entry : bodiesContactWatchers) {
                if (otherBody == entry.key && entry.value.breakForContact(fixtureA, fixtureB, action))
                    return true;
            }

            Fixture otherFixture = this.entity.fixtures.contains(fixtureA, true) ? fixtureB : fixtureA;
            for (ObjectMap.Entry<Fixture, ContactWatcher> entry : fixtureContactWatchers) {
                if (otherFixture == entry.key && entry.value.breakForContact(fixtureA, fixtureB, action))
                    return true;
            }
        }
        return false;
    }

}
