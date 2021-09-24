package com.nzt.box.bodies;

public class BodyDef {
    public BodyType bodyType;
    public boolean bullet = false; //check continus deplacement for collision
    public boolean sensor = false;
    public float mass = 1f;
    public float restitution = 0f; //energy return
    public float receive = 1f;
    public float transfert = 1f; //energy give
    public boolean canRotate = true;

    public BodyDef() {
        bodyType = BodyType.Static;
    }

    public BodyDef(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public BodyDef bodyType(BodyType bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public BodyDef bullet(boolean bullet) {
        this.bullet = bullet;
        return this;
    }

    public BodyDef sensor(boolean sensor) {
        this.sensor = sensor;
        return this;
    }

    public BodyDef mass(float mass) {
        this.mass = mass;
        return this;
    }

    public BodyDef restitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public BodyDef transfert(float transfert) {
        this.transfert = transfert;
        return this;
    }
    public BodyDef receive(float receive) {
        this.receive = receive;
        return this;
    }


    public BodyDef canRotate(boolean canRotate) {
        this.canRotate = canRotate;
        return this;
    }


}
