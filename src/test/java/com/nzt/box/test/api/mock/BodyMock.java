package com.nzt.box.test.api.mock;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

public class BodyMock extends Body {

    public BodyMock() {
        super(BodyType.Dynamic);
    }

    public BodyMock(String userData) {
        super(BodyType.Dynamic);
        this.userData = userData;
    }
}
