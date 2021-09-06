package com.nzt.box.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.vectors.V2;

public class ContactForces {

    private static Vector2 tmp1 = new Vector2();
    private static Vector2 tmp2 = new Vector2();
    private static Vector2 tmp3 = new Vector2();


    private static Vector3 tmp1V3 = new Vector3();

    public static void applyForces(ContactFixture contactFixture, float stepTime) {

        //check if bodyB apply forces to bodyA
        Fixture<?> fixtureA = contactFixture.fixtureA;
        Fixture<?> fixtureB = contactFixture.fixtureB;
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;

        bodyA.position.sub(tmp1V3.set(bodyA.velocity).scl(stepTime));
        fixtureA.changeBodyPosition(bodyA.position.x, bodyA.position.y);

        bodyB.position.add(tmp1V3.set(bodyB.velocity).scl(stepTime));
        fixtureB.changeBodyPosition(bodyB.position.x, bodyB.position.y);
        final boolean bodyBShouldApplyForces = fixtureB.testContact(fixtureA);//TODO fastcheck

        bodyA.position.add(tmp1V3.set(bodyA.velocity).scl(stepTime));
        fixtureA.changeBodyPosition(bodyA.position.x, bodyA.position.y);

        bodyB.position.sub(tmp1V3.set(bodyB.velocity).scl(stepTime));
        fixtureB.changeBodyPosition(bodyB.position.x, bodyB.position.y);

        Vector2 normal = contactFixture.collisionData.normal;

        Vector2 velocityA = bodyA.getVelocity(tmp1);
        Vector2 velocityB = bodyA.getVelocity(tmp2);

        float angleIncidence = V2.angleDeg(normal) - (V2.angleDeg(velocityA) - V2.angleDeg(normal));
        float angleReflexion = AngleUtils.incidenceToReflexion(angleIncidence);

        Vector2 dirForce = tmp3.set(1, 0).setAngleDeg(angleReflexion);
        float forceA = calculPowerImpact(bodyA);
        dirForce.setLength2(forceA);

        bodyB.velocity.set(dirForce,0);
        bodyA.velocity.setZero();
//        bodyB.forces.add()
    }

    public static float calculPowerImpact(Body body) {
        Vector2 velocity = body.getVelocity(tmp1);
        return 1 / 2f * body.mass * velocity.len2();
    }

    public float calculForceAfterImpact(Vector2 dir, float power, Body body) {
        return (float) Math.sqrt(power / (2 * body.mass));
    }
}
