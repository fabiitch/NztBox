package com.nzt.box.contact.compute;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.vectors.V2;

import static com.nzt.box.bodies.BodyType.*;

public class ContactForces {

    private static Vector2 tmp1 = new Vector2();
    private static Vector2 tmp2 = new Vector2();

    private static Vector3 tmp1V3 = new Vector3();

    public void calculReboundForces(ContactFixture contactFixture, float stepTime) {
        CollisionData data = contactFixture.collisionData;
        //check if bodyB apply forces to bodyA
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;

        //TEST B collides
        bodyB.position.add(tmp1V3.set(bodyB.velocity).scl(stepTime));
        fixtureB.changeBodyPosition(bodyB.position.x, bodyB.position.y);

        final boolean bodyBShouldApplyForces = fixtureB.testContact(fixtureA);//TODO fastcheck

        //RESET POS B
        bodyB.position.sub(tmp1V3.set(bodyB.velocity).scl(stepTime));
        fixtureB.changeBodyPosition(bodyB.position.x, bodyB.position.y);
//========================
        Vector2 normal = contactFixture.collisionData.normal;

        Vector2 velocityA = bodyA.getVelocity(tmp1);
        Vector2 velocityB = bodyB.getVelocity(tmp2);


        //rebound
        float angleIncidenceA = V2.angleDeg(normal) - (V2.angleDeg(velocityA) - V2.angleDeg(normal));
        float angleReflexionA = AngleUtils.incidenceToReflexion(angleIncidenceA);

        data.reboundA.set(1, 0).setAngleDeg(angleReflexionA);

        if (bodyBShouldApplyForces) {
            float angleIncidenceB = V2.angleDeg(normal) - (V2.angleDeg(velocityB) - V2.angleDeg(normal));
            float angleReflexionB = AngleUtils.incidenceToReflexion(angleIncidenceB);//TODO reutilisé A
            data.reboundB.set(1, 0).setAngleDeg(-angleReflexionB);
        }
//        //dirForces
        Vector2 forceA = calculPowerImpact(velocityA.cpy().sub(velocityB), bodyA, bodyB);
        Vector2 receiveB = forceA.cpy().scl(bodyB.receive);
        data.forceOnB.add(receiveB);
        float powerARebound = velocityA.cpy().scl(1 - bodyB.receive).len();
        float powerARestitution = forceA.cpy().scl(bodyB.restitution).len();
        data.reboundA.setLength(powerARebound + powerARestitution);

        if (bodyBShouldApplyForces) {
            Vector2 forceB = calculPowerImpact(velocityB.cpy().sub(velocityA), bodyB, bodyA);
            Vector2 receiveA = forceB.cpy().scl(bodyA.receive);
            data.forceOnA.add(receiveA);
            float powerBRebound = velocityB.cpy().scl(1 - bodyA.receive).len();
            float powerBRestitution = forceB.cpy().scl(bodyA.restitution).len();
            data.reboundB.setLength(powerBRebound + powerBRestitution);
        }
    }

    public void applyRebound(ContactFixture contactFixture) {
        CollisionData data = contactFixture.collisionData;

        Body bodyA = contactFixture.fixtureA.body;
        if (bodyA.bodyType != Static) {
            bodyA.setVelocity(data.reboundA);
        }

        Body bodyB = contactFixture.fixtureB.body;
        if (bodyB.bodyType != Static) {
            bodyB.setVelocity(data.reboundB);
        }
    }

    public void applyForces(ContactFixture contactFixture) {
        CollisionData data = contactFixture.collisionData;
        Body bodyA = contactFixture.fixtureA.body;
        if (bodyA.bodyType != Static) {
            Vector2 newVelA = bodyA.getVelocity(tmp1);
            newVelA.add(data.forceOnA);
            bodyA.setVelocity(newVelA);
        }

        Body bodyB = contactFixture.fixtureB.body;
        if (bodyB.bodyType != Static) {
            Vector2 newVelB = bodyB.getVelocity(tmp2);
            newVelB.add(data.forceOnB);
            bodyB.setVelocity(newVelB);
        }
    }

    public Vector2 calculPowerImpact(Vector2 velocityBodyACpy, Body bodyA, Body bodyB) {
        float scalarPower = bodyA.mass / bodyB.mass;
        return velocityBodyACpy.scl(scalarPower * (bodyA.transfert));
    }

    public static final short EVENT = 1;
    public static final short REBOUND_A = 1 << 2;
    public static final short REBOUND_B = 1 << 3;
    public static final short FORCES_ON_A = 1 << 4;
    public static final short FORCES_ON_B = 1 << 5;

    public short computeContact(ContactFixture contactFixture) {
        BodyType typeA = contactFixture.fixtureA.body.bodyType;
        BodyType typeB = contactFixture.fixtureB.body.bodyType;
        if (typeA == Static) {
            if (typeB == Static) {
                return 0;
            } else if (typeB == Dynamic) {
                return EVENT & REBOUND_B & FORCES_ON_B;
            } else if (typeB == Kinematic) {
                return EVENT;
            }
        } else if (typeA == Dynamic) {
            if (typeB == Static) {
                return EVENT & REBOUND_A & FORCES_ON_A;
            } else if (typeB == Dynamic) {
                return 0b11111;
            } else if (typeB == Kinematic) {
                return EVENT & REBOUND_A & FORCES_ON_A;
            }
        } else if (typeA == Kinematic) {
            if (typeB == Static) {
                return EVENT;
            } else if (typeB == Dynamic) {
                return EVENT & REBOUND_B & FORCES_ON_B;
            } else if (typeB == Kinematic) {
                return EVENT;
            }
        }
        return 0;
    }
}
