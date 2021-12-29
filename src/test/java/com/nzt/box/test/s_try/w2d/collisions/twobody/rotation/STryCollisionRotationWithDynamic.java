package com.nzt.box.test.s_try.w2d.collisions.twobody.rotation;

import com.nzt.box.bodies.BodyType;
import com.nzt.gdx.test.utils.archi.mains.dev.FastTesterMain;

public class STryCollisionRotationWithDynamic extends STryCollisionRotationWithStatic {
    public STryCollisionRotationWithDynamic(FastTesterMain main) {
        super(main);
        body2.bodyType= BodyType.Dynamic;
    }
}
