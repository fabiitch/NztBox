package com.nzt.box.test.screens.w2d.collisions.twobody.rotation;

import com.nzt.box.bodies.BodyType;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;

public class STCollisionRotationWithDynamic extends STCollisionRotationWithStatic {
    public STCollisionRotationWithDynamic(FastTesterMain main) {
        super(main);
        body2.bodyType= BodyType.Dynamic;
    }
}
