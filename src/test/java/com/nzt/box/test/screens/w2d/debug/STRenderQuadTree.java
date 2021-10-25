package com.nzt.box.test.screens.w2d.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.debug.render.g2d.Box2DDebugRenderer;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.math.quadtree.QuadTreeHelper;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.trials.tester.archi.mains.FastTesterMain;
import com.nzt.gdx.test.trials.tester.archi.screens.TestScreen;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;
import com.nzt.gdx.utils.GdxUtils;

@TestScreenList(group = "2D.debug")
public class STRenderQuadTree extends TestScreen {

    QuadTree quadTree = new QuadTree();
    Box2DDebugRenderer box2DDebugRenderer;

    public STRenderQuadTree(FastTesterMain main) {
        super(main);
        box2DDebugRenderer = new Box2DDebugRenderer();
        box2DDebugRenderer.debugSettings.drawQuadTree = true;
        box2DDebugRenderer.debugSettings.drawQuadTreeData = true;

        quadTree.init(new QuadTreeHelper(quadTree),  GdxUtils.screenAsRectangle(new Rectangle(), false), 10, 10);
        Gdx.input.setInputProcessor(inputProcessor());

        infoMsg("Left/Right click for split/group");
    }


    public InputProcessor inputProcessor() {
        return new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                Vector2 clickPos = getClickPos(screenX, screenY);
                QuadTree quadTreeGet = STRenderQuadTree.this.quadTree.getQuadTree(clickPos.x, clickPos.y);
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
        box2DDebugRenderer.drawQuadTree(quadTree);
        box2DDebugRenderer.shapeRenderer.end();

        box2DDebugRenderer.spriteBatch.begin();
        box2DDebugRenderer.drawQuadTreeData(quadTree);
        box2DDebugRenderer.spriteBatch.end();
    }

    @Override
    public void disposeTestScreen() {
        box2DDebugRenderer.dispose();
    }
}
