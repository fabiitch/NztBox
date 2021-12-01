package com.nzt.box.test.screens.base.debugger;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;

public interface BreakPoint {

    boolean breakForBody(Body body, BreakPoints action);

    boolean breakForFixture(Fixture fixture, BreakPoints action);

    String type();
}
