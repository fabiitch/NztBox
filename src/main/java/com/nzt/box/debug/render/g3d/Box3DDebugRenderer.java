package com.nzt.box.debug.render.g3d;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.nzt.box.debug.render.BoxDebugRender;
import com.nzt.box.world.World;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class Box3DDebugRenderer extends BoxDebugRender {
    public NzShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;

    public Box3DDebugRenderer() {
        this.shapeRenderer = new NzShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        bitmapFont = new BitmapFont();
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(World world, Matrix4 combined) {

    }

    @Override
    public void dispose() {

    }
}
