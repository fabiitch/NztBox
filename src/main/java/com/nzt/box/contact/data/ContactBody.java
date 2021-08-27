package com.nzt.box.contact.data;

import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Body;

public class ContactBody implements Pool.Poolable{
    public Body bodyA;
    public Body bodyB;



    @Override
    public void reset() {

    }
}
