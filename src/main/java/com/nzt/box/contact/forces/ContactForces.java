package com.nzt.box.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.vectors.V2;

public class ContactForces {

    private static Vector2 tmp1 = new Vector2();
    private static Vector2 tmp2 = new Vector2();

    private static Vector3 tmp1V3 = new Vector3();

    public static void calculForces(ContactFixture contactFixture, float stepTime) {
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

        Vector2 velocityACpy = velocityA.cpy();
        Vector2 velocityBCpy = velocityB.cpy();
        //rebound
        if (bodyA.bouncing > 0) {
            float angleIncidence = V2.angleDeg(normal) - (V2.angleDeg(velocityA) - V2.angleDeg(normal));
            float angleReflexion = AngleUtils.incidenceToReflexion(angleIncidence);

            data.reboundA.set(1, 0).setAngleDeg(angleReflexion);
        }

        if (bodyBShouldApplyForces && bodyB.bouncing > 0) {
            float angleIncidence = V2.angleDeg(normal) - (V2.angleDeg(velocityB) - V2.angleDeg(normal));
            float angleReflexion = AngleUtils.incidenceToReflexion(angleIncidence);
            data.reboundA.set(1, 0).setAngleDeg(-angleReflexion);
        }
//        //dirForces
        Vector2 forceA = calculPowerImpact(velocityACpy, bodyA, bodyB);
        data.forceOnB.set(forceA);
        if (bodyBShouldApplyForces) {
            Vector2 forceB = calculPowerImpact(velocityBCpy, bodyB, bodyA);
            data.forceOnA.set(forceB);
        }
    }

    public static void applyRebound(ContactFixture contactFixture) {
        CollisionData data = contactFixture.collisionData;
        Body bodyA = contactFixture.fixtureA.body;
        Vector2 newVelA = bodyA.getVelocity(tmp1);
        V2.setAngle(newVelA, data.reboundA);
        bodyA.setVelocity(newVelA);

        Body bodyB = contactFixture.fixtureB.body;
        Vector2 newVelB = bodyB.getVelocity(tmp2);
        V2.setAngle(newVelB, data.reboundB);
        newVelB.add(data.forceOnB);
        bodyB.setVelocity(newVelB);
    }

    public static void applyForces(ContactFixture contactFixture) {
        CollisionData data = contactFixture.collisionData;
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;

        Vector2 newVelA = bodyA.getVelocity(tmp1);
        newVelA.add(data.forceOnA);
        bodyA.setVelocity(newVelA);

        Vector2 newVelB = bodyB.getVelocity(tmp2);
        newVelB.add(data.forceOnB);
        bodyB.setVelocity(newVelB);
    }

    public static Vector2 calculPowerImpact(Vector2 velocityBodyACpy, Body bodyA, Body bodyB) {
        float scalarPower = bodyA.mass / bodyB.mass * bodyA.transfert * bodyB.receive;
        return velocityBodyACpy.scl(scalarPower);
    }

}
