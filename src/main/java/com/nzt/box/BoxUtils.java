package com.nzt.box;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.data.ContactFixture;

public class BoxUtils {

    public static boolean isContactBlock(Body bodyA, Body bodyB) {
        return isStaticDynamic(bodyA, bodyB) || isTwoType(bodyA, bodyB, BodyType.Dynamic);
    }

    public static boolean isStaticDynamic(ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;
        return isOneType(bodyA, bodyB, BodyType.Dynamic) && isOneType(bodyA, bodyB, BodyType.Static);
    }

    public static boolean isStaticDynamic(Body bodyA, Body bodyB) {
        return isOneType(bodyA, bodyB, BodyType.Dynamic) && isOneType(bodyA, bodyB, BodyType.Static);
    }

    public static boolean isOneType(Body bodyA, Body bodyB, BodyType bodyType) {
        return bodyA.bodyType == bodyType || bodyB.bodyType == bodyType;
    }

    public static boolean isTwoType(Body bodyA, Body bodyB, BodyType bodyType) {
        return bodyA.bodyType == bodyType && bodyB.bodyType == bodyType;
    }

    public static boolean isTwoType(Body bodyA, Body bodyB, BodyType bodyType1, BodyType bodyType2) {
        return bodyA.bodyType != bodyB.bodyType && isOneType(bodyA, bodyB, bodyType1) && isOneType(bodyA, bodyB, bodyType2);
    }
}
