package com.nzt.box;

import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;

public class BoxUtils {

//    public static boolean isContactBlock(Body bodyA, Body bodyB) {
////        isOneType(bodyA, bodyB, BodyType.Dynamic) && isOneType(bodyA, bodyB, BodyType.S)
//    }

    public static boolean isStaticDynamic(Body bodyA, Body bodyB) {
        return isOneType(bodyA, bodyB, BodyType.Dynamic) && isOneType(bodyA, bodyB, BodyType.Static);
    }

    public static boolean isOneType(Body bodyA, Body bodyB, BodyType bodyType) {
        return bodyA.bodyType == bodyType || bodyB.bodyType == bodyType;
    }

    public static boolean isTwoType(Body bodyA, Body bodyB, BodyType bodyType) {
        return bodyA.bodyType == bodyType && bodyB.bodyType == bodyType;
    }
}
