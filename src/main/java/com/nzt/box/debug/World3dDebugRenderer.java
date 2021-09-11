package com.nzt.box.debug;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.nzt.box.world.World;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class World3dDebugRenderer extends WorldDebugRender {
    public NzShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;

    public World3dDebugRenderer() {
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
