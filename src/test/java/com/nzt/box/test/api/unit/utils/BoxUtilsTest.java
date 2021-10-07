package com.nzt.box.test.api.unit.utils;

import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoxUtilsTest {
    Body kinematic = new Body(BodyType.Kinematic);
    Body statik = new Body(BodyType.Static);
    Body ghost = new Body(BodyType.Ghost);
    Body dynamic = new Body(BodyType.Dynamic);

    @Test
    public void isOneTypeTest() {
        assertTrue(BoxUtils.isOneType(kinematic, statik, BodyType.Static));
        assertTrue(BoxUtils.isOneType(kinematic, kinematic, BodyType.Kinematic));
        assertFalse(BoxUtils.isOneType(kinematic, statik, BodyType.Dynamic));
        assertFalse(BoxUtils.isOneType(kinematic, statik, BodyType.Ghost));
    }

    @Test
    public void isTwoTypeTest() {
        assertTrue(BoxUtils.isTwoType(statik, statik, BodyType.Static));
        assertTrue(BoxUtils.isTwoType(kinematic, kinematic, BodyType.Kinematic));
        assertFalse(BoxUtils.isTwoType(kinematic, statik, BodyType.Static));
        assertFalse(BoxUtils.isTwoType(kinematic, statik, BodyType.Kinematic));
    }
}
