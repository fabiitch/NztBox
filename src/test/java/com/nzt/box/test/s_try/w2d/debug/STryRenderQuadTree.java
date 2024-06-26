package com.nzt.box.test.s_try.w2d.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.render.g2d.Box2DDebugRenderer;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeContainer;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.utils.GdxTestUtils;
import com.nzt.gdx.mains.FastTesterMain;
import com.nzt.gdx.test.utils.archi.screens.ScreenTry;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.debug")
public class STryRenderQuadTree extends ScreenTry {

    QuadTreeContainer container;
    Box2DDebugRenderer box2DDebugRenderer;

    public STryRenderQuadTree(FastTesterMain main) {
        super(main);
        box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.debugSettings.drawQuadTree = true;
        box2DDebugRenderer.debugSettings.drawQuadTreeData = true;
        container = new QuadTreeContainer(GdxTestUtils.screenAsRectangle(false), 10, 10);
        Gdx.input.setInputProcessor(inputProcessor());

        infoMsg("Left/Right click for split/group");
    }


    public InputProcessor inputProcessor() {
        return new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = getClickPos(screenX, screenY);
                QuadTree quadTreeGet = container.root.getQuadTree(clickPos.x, clickPos.y);
                if (quadTreeGet != null) {
                    if (button == LEFT_CLICK) {
                        quadTreeGet.split();
                    } else {
                        if (quadTreeGet.parent != null)
                            quadTreeGet.parent.regroup();
                    }
                }
                return false;
            }
        };
    }

    @Override
    public String getTestExplication() {
        return "Test render quadtree";
    }

    @Override
    public void renderTestScreen(float dt) {
        box2DDebugRenderer.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        box2DDebugRenderer.shapeRenderer.setColor(Color.RED);
        box2DDebugRenderer.drawQuadTree(container.root);
        box2DDebugRenderer.shapeRenderer.end();

        box2DDebugRenderer.spriteBatch.begin();
        box2DDebugRenderer.drawQuadTreeData(container.root);
        box2DDebugRenderer.spriteBatch.end();
    }

    @Override
    public void disposeTestScreen() {
        box2DDebugRenderer.dispose();
    }
}
