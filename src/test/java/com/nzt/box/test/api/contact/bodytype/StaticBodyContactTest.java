package com.nzt.box.test.api.contact.bodytype;

import com.nzt.box.bodies.BodyType;

public class StaticBodyContactTest extends BaseBodyTypeContactTest {

    @Override
    public BodyType bodyTypeToTest() {
        return BodyType.Static;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean canRotate() {
        return false;
    }

    @Override
    public BodyTypeTestResult withStatic() {
        return null;
    }

}
