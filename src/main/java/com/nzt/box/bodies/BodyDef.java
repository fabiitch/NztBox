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

    public BodyDef cpy() {
        BodyDef cpy = new BodyDef();
        cpy.bodyType = this.bodyType;
        cpy.bullet = this.bullet;
        cpy.sensor = this.sensor;
        cpy.mass = this.mass;
        cpy.restitution = this.restitution;
        cpy.receive = this.receive;
        cpy.transfert = this.transfert;
        cpy.canRotate = this.canRotate;
        return cpy;
    }

    public BodyDef applyToBody(Body body) {
        body.bodyType = this.bodyType;
        body.bullet = this.bullet;
        body.sensor = this.sensor;
        body.mass = this.mass;
        body.restitution = this.restitution;
        body.receive = this.receive;
        body.transfert = this.transfert;
        body.canRotate = this.canRotate;
        return this;
    }

    public BodyDef getFromBody(Body body) {
        this.bodyType = body.bodyType;
        this.bullet = body.bullet;
        this.sensor = body.sensor;
        this.mass = body.mass;
        this.restitution = body.restitution;
        this.receive = body.receive;
        this.transfert = body.transfert;
        this.canRotate = body.canRotate;
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
