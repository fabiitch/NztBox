package com.nzt.box.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.world.World;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class World2dDebugRenderer extends WorldDebugRender {

    public NzShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;

    public World2dDebugRenderer() {
        this.shapeRenderer = new NzShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        bitmapFont = new BitmapFont();
        spriteBatch = new SpriteBatch();
    }


    private Vector2 tmp1 = new Vector2();
    private Vector2 tmp2 = new Vector2();

    public void render(World world, Matrix4 projMatrix) {
        shapeRenderer.setProjectionMatrix(projMatrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Array<Body>bodies = world.data.bodies;
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            if (!body.active && debugSettings.drawInactive)
                continue;
            Array<Fixture<?>> fixtures = body.fixtures;
            shapeRenderer.setColor(Color.CYAN);
            for (int j = 0, m = fixtures.size; j < m; j++) {
                fixtures.get(j).bodyShape.draw(shapeRenderer);
            }
            if (debugSettings.drawVelocity) {
                body.getPosition(tmp1);
                body.getVelocity(tmp2);
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.line(tmp1, tmp1.cpy().add(tmp2.scl(0.2f)));
            }
        }
        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(projMatrix);
        for (int i = 0, n = bodies.size; i < n; i++) {
            Body body = bodies.get(i);
            body.getPosition(tmp1);
            if (body.userData != null)
                bitmapFont.draw(spriteBatch, body.userData.toString(), tmp1.x, tmp1.y);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
