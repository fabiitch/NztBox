package com.nzt.box.math.quadtree.pools;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nzt.box.bodies.Fixture;

public class FixtureArrayPool extends Pool<Array<Fixture>> {
    @Override
    protected Array<Fixture> newObject() {
        return new Array<>();
    }

    @Override
    public void free(Array<Fixture> object) {
        super.free(object);
        object.clear();
    }
}
