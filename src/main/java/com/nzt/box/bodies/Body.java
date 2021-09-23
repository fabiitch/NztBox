package com.nzt.box.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.forces.Force;
import com.nzt.box.contact.data.ContactBody;
import com.nzt.gdx.math.vectors.V3;

//TODO POOLABLE
public class Body implements Pool.Poolable {

    public int id;
    public BodyType bodyType;
    public Object userData;
    public boolean active = true;
    public boolean bullet = false; //check continus deplacement for collision
    public boolean sensor = false;
    public int categorie = 0;

    public final Vector3 position = new Vector3();
    public final Vector3 velocity = new Vector3();

    public final Array<Fixture<?>> fixtures;
    public final Array<ContactBody> contacts;
    public final Array<Force> forces;
    public final Array<Force> forcesToRemove;

    public float mass = 1f;
    public float transfert = 1f; //energy transfert
    public float receive = 1f; //energy get of transfert
    public float restitution = 0f; //energy return of transfert

    public boolean canRotate = true;

    public float maxDstFixture; //dst la plus eloigné du body
    public float minDstFixture;//dst mini ou on est forcément en contact
    public boolean dirty = true;

    private final Vector3 tmp = new Vector3();

    public Body(BodyDef bodyDef) {
        this(bodyDef.bodyType);
        this.bullet = bodyDef.bullet;
        this.sensor = bodyDef.sensor;
        this.mass = bodyDef.mass;
        this.restitution = bodyDef.restitution;
        this.transfert = bodyDef.transfert;
        this.canRotate = bodyDef.canRotate;
    }

    public Body(BodyType bodyType) {
        this.bodyType = bodyType;
        fixtures = new Array<>();
        contacts = new Array<>();
        forces = new Array<>();
        forcesToRemove = new Array<>();
    }

    public boolean move(float stepTime) {
        for (Force force : forces) {
            boolean toRemove = force.applyToBody(stepTime, this);
            if (toRemove)
                forcesToRemove.add(force);
        }
        forces.removeAll(forcesToRemove, true);

        if (velocity.isZero())
            return false;
        position.add(tmp.set(velocity).scl(stepTime));
        updatePosition();
        return true;
    }

    public void addForce(Force force) {
        forces.add(force);
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
        fixture.body = this;
        updatePosition();
        this.maxDstFixture = Math.max(this.maxDstFixture, fixture.bodyShape.maxDst);
        this.minDstFixture = Math.max(this.minDstFixture, fixture.bodyShape.minDst);
    }

    public Vector2 getPosition(Vector2 position2D) {
        position2D.x = position.x;
        position2D.y = position.y;
        return position2D;
    }

    public Vector2 getVelocity(Vector2 velocity) {
        velocity.x = this.velocity.x;
        velocity.y = this.velocity.y;
        return velocity;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        updatePosition();
    }

    public void setPosition(Vector2 position) {
        this.setPosition(position.x, position.y);
    }

    public void setPosition(Vector3 position) {
        this.position.set(position);
        updatePosition();
    }

    public void updatePosition() {
        for (int i = 0, n = fixtures.size; i < n; i++) {
            Fixture fixture = fixtures.get(i);
            fixture.changeBodyPosition(position.x, position.y);
        }
        dirty = true;
    }

    public void setVelocity(Vector3 velocity) {
        velocity.set(velocity);
    }

    public void setVelocity(float x, float y, float z) {
        velocity.set(x, y, z);
    }

    public void setVelocity(Vector2 velocity) {
        V3.set(this.velocity, velocity);
    }

    public void setVelocity(float x, float y) {
        if (bodyType != BodyType.Static) {
            velocity.x = x;
            velocity.y = y;
        }
    }

    @Override
    public void reset() {

    }
}
