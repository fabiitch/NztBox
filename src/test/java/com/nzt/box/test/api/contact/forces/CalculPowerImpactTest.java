package com.nzt.box.test.api.contact.forces;

import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.contact.compute.ContactForces;
import com.nzt.gdx.test.api.math.vectors.VTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculPowerImpactTest {
    ContactForces contactForces = new ContactForces();
    BodyDef bodyDef1, bodyDef2;
    Body body1, body2;
    Vector2 energy = new Vector2();

    @BeforeEach
    public void init() {
        bodyDef1 = new BodyDef(BodyType.Dynamic).mass(1).transfert(1);
        bodyDef2 = new BodyDef(BodyType.Dynamic).mass(1).transfert(1);
        body1 = new Body(bodyDef1);
        body2 = new Body(bodyDef2);
    }

    @Test
    public void oneMoveT1() {
        bodyDef1.mass(1).transfert(1).applyToBody(body1);
        bodyDef2.mass(1).transfert(1).applyToBody(body2);
        energy = contactForces.calculPowerImpact(v(100, 0), v(0, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(100, 0), energy);
    }

    @Test
    public void oneMoveT2() {
        bodyDef1.mass(10).transfert(1).applyToBody(body1);
        bodyDef2.mass(1).transfert(1).applyToBody(body2);
        energy = contactForces.calculPowerImpact(v(100, 0), v(0, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(1000, 0), energy);
    }

    @Test
    public void oneMoveT3() {
        bodyDef1.mass(10).transfert(0).applyToBody(body1);
        bodyDef2.mass(1).transfert(1).applyToBody(body2);
        energy = contactForces.calculPowerImpact(v(100, 0), v(0, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(0, 0), energy);
    }

    @Test
    public void frontalT1() {
        bodyDef1.mass(1).transfert(1).applyToBody(body1);
        bodyDef2.mass(1).transfert(1).applyToBody(body2);
        energy = contactForces.calculPowerImpact(v(100, 0), v(-100, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(100, 0), energy);

        energy = contactForces.calculPowerImpact(v(50, 0), v(-100, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(50, 0), energy);

        energy = contactForces.calculPowerImpact(v(150, 0), v(100, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(50, 0), energy);

        energy = contactForces.calculPowerImpact(v(-50, 0), v(-100, 0), body1, body2);
        VTestUtils.assertEquals(new Vector2(0, 0), energy);
    }

    private Vector2 v(float x, float y) {
        return new Vector2(x, y);
    }
}
