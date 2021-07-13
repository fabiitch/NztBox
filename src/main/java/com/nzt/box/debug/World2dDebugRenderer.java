package com.nzt.box.debug;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nzt.box.World;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class World2dDebugRenderer implements WorldDebugRender {

    public NzShapeRenderer shapeRenderer;

    public World2dDebugRenderer(NzShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public World2dDebugRenderer() {
        this.shapeRenderer = new NzShapeRenderer();
    }

    public void render(World world, Matrix4 projMatrix) {
        shapeRenderer.setProjectionMatrix(projMatrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        SnapshotArray<Body> bodies = world.bodies;
        for (Body body : bodies) {
            SnapshotArray<Fixture> fixtures = body.fixtures;
            for (Fixture fixture : fixtures) {
                fixture.bodyShape.draw(shapeRenderer);
            }
        }
        shapeRenderer.end();
    }
}
