//package com.nzt.box.contact.compute;
//
//import com.nzt.box.bodies.Body;
//import com.nzt.box.bodies.BodyType;
//
//import static com.nzt.box.bodies.BodyType.*;
//
//public class ContactCompute {
//
//    public static final short EVENT = 1;
//    public static final short REBOUND_A = 1 << 2;
//    public static final short REBOUND_B = 1 << 3;
//    public static final short FORCES_ON_A = 1 << 4;
//    public static final short FORCES_ON_B = 1 << 5;
//
//    public static short computeContact(Body bodyA, Body bodyB) {
//        BodyType typeA = bodyA.bodyType;
//        BodyType typeB = bodyB.bodyType;
//        if (typeA == Static) {
//            if (typeB == Static) {
//                return 0;
//            } else if (typeB == Dynamic) {
//                return EVENT & REBOUND_B & FORCES_ON_B;
//            } else if (typeB == Kinematic) {
//                return EVENT;
//            }
//        } else if (typeA == Dynamic) {
//            if (typeB == Static) {
//                return EVENT & REBOUND_A & FORCES_ON_A;
//            } else if (typeB == Dynamic) {
//                return 0b11111;
//            } else if (typeB == Kinematic) {
//                return EVENT & REBOUND_A & FORCES_ON_A;
//            }
//        } else if (typeA == Kinematic) {
//            if (typeB == Static) {
//                return EVENT;
//            } else if (typeB == Dynamic) {
//                return EVENT & REBOUND_B & FORCES_ON_B;
//            } else if (typeB == Kinematic) {
//                return EVENT;
//            }
//        }
//        return 0;
//    }
//
////    public static boolean contactEvent(Body bodyA, Body bodyB) {
////        BodyType typeA = bodyA.bodyType;
////        BodyType typeB = bodyB.bodyType;
////
////        if (typeA == BodyType.Static) {
////            if (typeB == BodyType.Dynamic ||
////                    typeB == BodyType.Ghost ||
////                    typeB == BodyType.Dynamic ||
////                    typeB == BodyType.Dynamic ||
////            ) {
////                return true;
////            }
////        }
////        if (typeA == BodyType.Dynamic) {
////            if (typeB == BodyType.Static ||
////                    typeB == BodyType.Dynamic ||
////                    typeB == BodyType.Kinematic ||
////                    typeB == BodyType.Ghost ||
////                    typeB == BodyType.Force) {
////                return true;
////            }
////        }
////        if (typeA == BodyType.Kinematic) {
////            if (typeB == BodyType.Dynamic ||
////                    typeB == BodyType.Kinematic ||
////                    typeB == BodyType.Ghost ||
////                    typeB == BodyType.Force) {
////                return true;
////            }
////        }
////        return false;
////    }
//
//
//}
