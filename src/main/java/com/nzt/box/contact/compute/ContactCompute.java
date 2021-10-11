package com.nzt.box.contact.compute;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nzt.box.BoxUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.CollisionData;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.gdx.math.AngleUtils;
import com.nzt.gdx.math.NzMath;
import com.nzt.gdx.math.vectors.V2;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;
import static com.nzt.box.bodies.BodyType.*;
//https://www.myphysicslab.com/engine2D/contact-en.html
public class ContactCompute {

    private static Vector2 tmp1 = new Vector2();
    private static Vector2 tmp2 = new Vector2();

    private static Vector3 tmp1V3 = new Vector3();


    public void computeContact(ContactFixture contactFixture) {
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;
        if (BoxUtils.isTwoType(bodyA, bodyB, Dynamic)) {
            calculImpact(bodyA, bodyB, contactFixture.collisionData, true);
            calculImpact(bodyB, bodyA, contactFixture.collisionData, false);
        } else {
            calculRebound(contactFixture);
        }
        //TODO kinematic
    }

    //TODO https://perso.liris.cnrs.fr/nicolas.pronost/UUCourses/GamePhysics/lectures/lecture%207%20Collision%20Resolution.pdf
    /**
     * http://www.sciencecalculators.org/mechanics/collisions/
     */
    public void calculImpact(Body bodyA, Body bodyB, CollisionData data, boolean A) {

        Vector2 v1 = bodyA.getVelocity(tmp1);
        Vector2 v2 = bodyB.getVelocity(tmp2);

        float mass1 = bodyA.mass;
        float mass2 = bodyB.mass;
        float angle1 = v1.angleRad();
        float angle2 = v2.angleRad();
        float angleCol = V2.angleRad(data.normal) - (V2.angleRad(v1) - V2.angleRad(data.normal));
        float lenV1 = v1.len() * bodyA.transfert * bodyB.receive;
        float lenV2 = v2.len() * bodyB.transfert * bodyA.receive;

        float x = lenV1 * (cos(angle1 - angleCol)) * (mass1 - mass2);
        x += 2 * mass2 * lenV2 * cos(angle2 - angleCol);
        x /= (mass1 + mass2);
        x *= cos(angleCol);
        x += lenV1 * sin(angle1 - angleCol) * cos(angleCol + (MathUtils.PI / 2));

        float y = (lenV1 * (cos(angle1 - angleCol)) * (mass1 - mass2)) + 2 * mass2 * lenV2 * cos(angle2 - angleCol);
        y /= (mass1 + mass2);
        y *= sin(angleCol);
        y += lenV1 * sin(angle1 - angleCol) * sin(angleCol + (MathUtils.PI / 2));

        if (A)
            data.newVelA.set(x, y);
        else
            data.newVelB.set(x, y);
    }

    public void calculRebound(ContactFixture contactFixture) {
        CollisionData data = contactFixture.collisionData;
        //check if bodyB apply forces to bodyA
        Body bodyA = contactFixture.fixtureA.body;
        Body bodyB = contactFixture.fixtureB.body;

        Vector2 normal = contactFixture.collisionData.normal;

        Vector2 velocityA = bodyA.getVelocity(tmp1);

        //rebound
        float angleIncidenceA = V2.angleDeg(normal) - (V2.angleDeg(velocityA) - V2.angleDeg(normal));
        float angleReflexionA = AngleUtils.incidenceToReflexion(angleIncidenceA);

        float powerRebound = velocityA.len() * bodyA.transfert * bodyB.restitution;
        data.newVelA.set(powerRebound, 0).setAngleDeg(angleReflexionA);
    }

    public void applyResult(ContactFixture contactFixture) {
        CollisionData data = contactFixture.collisionData;

        Body bodyA = contactFixture.fixtureA.body;
        if (bodyA.bodyType != Static) {
            bodyA.setVelocity(data.newVelA);
        }

        Body bodyB = contactFixture.fixtureB.body;
        if (bodyB.bodyType != Static) {
            bodyB.setVelocity(data.newVelB);
        }
    }
}
