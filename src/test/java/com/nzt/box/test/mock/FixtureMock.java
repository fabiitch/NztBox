package com.nzt.box.test.mock;

import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;

/**
 * Simple Circle Fixture
 */
public class FixtureMock extends Fixture<CircleShape> {

    public FixtureMock() {
        super(new CircleShape(1));
    }

    public FixtureMock(String userData) {
        super(new CircleShape(1));
        this.userData = userData;
    }
}
