package com.nzt.box.math;

import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.QuadTreeFloat;

public class QuadTreeShape implements Pool.Poolable {
    public @Null QuadTreeFloat nw, ne, sw, se;

     private final Pool<QuadTreeShape> pool = new Pool(128, 4096) {
        protected QuadTreeShape newObject () {
            return new QuadTreeShape();
        }
    };

    @Override
    public void reset() {

    }
}
