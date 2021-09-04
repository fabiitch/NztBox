package com.nzt.box.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.vectors.V2;

public class ContactForces {

    private static Vector2 tmp1 = new Vector2();
    private static Vector2 tmp2 = new Vector2();
    private static Vector2 tmp3 = new Vector2();

    public static void applyForces(ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;

        Vector2 normal = contactFixture.collisionData.normal;

        Vector2 velocityA = bodyA.getVelocity(tmp1);
        Vector2 velocityB = bodyA.getVelocity(tmp2);

        float angleIncidence = V2.angleDeg(normal) - (V2.angleDeg(velocityA) - V2.angleDeg(normal));
        float angleReflexion = AngleUtils.incidenceToReflexion(angleIncidence);

        Vector2 dirForce = tmp3.set(1, 0).setAngleDeg(angleReflexion);
        float forceA = calculPowerImpact(bodyA);
        dirForce.setLength2(forceA);

//        bodyB.forces.add()
    }

    public static float calculPowerImpact(Body body) {
        Vector2 velocity = body.getVelocity(tmp1);
        return 1 / 2 * body.mass * velocity.len2();
    }

    public float calculForceAfterImpact(Vector2 dir, float power, Body body) {
        return (float) Math.sqrt(power / (2 * body.mass));
    }
}
