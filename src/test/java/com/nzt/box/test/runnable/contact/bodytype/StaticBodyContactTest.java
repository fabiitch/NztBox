package com.nzt.box.test.runnable.contact.bodytype;

import com.nzt.box.bodies.BodyType;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class StaticBodyContactTest extends BaseBodyTypeContactTest {

    public StaticBodyContactTest(FastTesterMain main) {
        super(main);
    }

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
