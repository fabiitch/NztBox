package com.nzt.box.test.mock;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

/**
 * Simple Dynamic Body
 */
public class BodyMock extends Body {

    public BodyMock() {
        super(BodyType.Dynamic);
        addFixture(new FixtureMock("Fmock"));
    }

    public BodyMock(BodyType bodyType) {
        super(bodyType);
        addFixture(new FixtureMock("Fmock"));
    }

    public BodyMock(String userData) {
        super(BodyType.Dynamic);
        this.userData = userData;
        addFixture(new FixtureMock(userData));
    }

    public BodyMock(BodyType bodyType, String userData) {
        super(bodyType);
        this.userData = userData;
        addFixture(new FixtureMock(userData));
    }

    public BodyMock(float posX, float posY) {
        super(BodyType.Dynamic);
        addFixture(new FixtureMock());
        setPosition(posX, posY);
    }
}
