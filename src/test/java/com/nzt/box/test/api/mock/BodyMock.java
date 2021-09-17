package com.nzt.box.test.api.mock;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

/**
 * Simple Dynamic Body
 */
public class BodyMock extends Body {

    public BodyMock() {
        super(BodyType.Dynamic);
    }

    public BodyMock(String userData) {
        super(BodyType.Dynamic);
        this.userData = userData;
    }

    public BodyMock(String userData, boolean addFixtureMock) {
        super(BodyType.Dynamic);
        this.userData = userData;
        addFixture(new FixtureMock(userData));
    }
}
