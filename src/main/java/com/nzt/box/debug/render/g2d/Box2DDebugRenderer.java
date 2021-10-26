package com.nzt.box.debug.render.g2d;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.debug.render.BoxDebugRender;
import com.nzt.box.math.quadtree.QuadTree;
import com.nzt.box.world.World;
import com.nzt.gdx.graphics.renderers.NzShapeRenderer;

public class Box2DDebugRenderer extends BoxDebugRender {

    public NzShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    public BitmapFont bitmapFont;

    public Box2DDebugRenderer() {
        shapeRenderer = new NzShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        this.shapeRenderer = new NzShapeRenderer();
        bitmapFont = new BitmapFont();
        spriteBatch = new SpriteBatch();
    }


    private Vector2 tmp1 = new Vector2();
    private Vector2 tmp2 = new Vector2();
    private Array<ContactFixture> contactDraw = new Array<>();

    public void render(World world, Matrix4 projMatrix) {
        shapeRenderer.setProjectionMatrix(projMatrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Array<Body> bodies = world.data.bodies;
        for (int i1 = 0, n1 = bodies.size; i1 < n1; i1++) {
            Body body = bodies.get(i1);
            if (!body.active && debugSettings.drawInactive)
                continue;
            Array<Fixture<?>> fixtures = body.fixtures;
            for (int i2 = 0, n2 = fixtures.size; i2 < n2; i2++) {
                Fixture fixture = fixtures.get(i2);
                shapeRenderer.setColor(debugSettings.getColorBody(body));
                fixture.bodyShape.draw(shapeRenderer);

                shapeRenderer.setColor(debugSettings.colorBoundingRect);
                shapeRenderer.rect(fixture.bodyShape.boundingRect);

                if (debugSettings.drawContactPoint) {
                    Array<ContactFixture> contacts = fixture.contacts;
                    shapeRenderer.setColor(debugSettings.colorContactPoint);
                    for (int i3 = 0, n3 = fixture.contacts.size; i3 < n3; i3++) {
                        ContactFixture contactFixture = contacts.get(i3);
                        if (!contactDraw.contains(contactFixture, true)) {
                            Vector2 collisionPoint = contactFixture.collisionData.collisionPoint;
                            contactDraw.add(contactFixture);
                            shapeRenderer.line(tmp1.set(collisionPoint).add(5, 0), tmp2.set(collisionPoint).add(-5, 0));
                            shapeRenderer.line(tmp1.set(collisionPoint).add(0, 5), tmp2.set(collisionPoint).add(0, -5));
                        }
                    }
                }
            }
            if (debugSettings.drawVelocity) {
                body.getPosition(tmp1);
                body.getVelocity(tmp2);
                shapeRenderer.setColor(debugSettings.colorVelocity);
                shapeRenderer.line(tmp1, tmp1.cpy().add(tmp2.scl(0.2f)));
            }

        }
        if (debugSettings.drawQuadTree) {
            drawQuadTree(world.data.quadTree);
        }

        shapeRenderer.end();
        contactDraw.clear();

        if (debugSettings.drawBodyUserData) {
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

        if (debugSettings.drawQuadTreeData) {
            spriteBatch.begin();
            spriteBatch.setProjectionMatrix(projMatrix);
            drawQuadTreeData(world.data.quadTree);
            spriteBatch.end();
        }
    }

    public void drawQuadTreeData(QuadTree quadTree) {
        if (quadTree.isSplitted) {
            drawQuadTreeData(quadTree.ne);
            drawQuadTreeData(quadTree.nw);
            drawQuadTreeData(quadTree.se);
            drawQuadTreeData(quadTree.sw);
        } else {
            Rectangle rect = quadTree.boundingRect;
            bitmapFont.draw(spriteBatch, quadTree.valuesCount + "", rect.x + rect.width/2, rect.y +rect.height/2);
        }
    }

    public void drawQuadTree(QuadTree quadTree) {
        shapeRenderer.setColor(debugSettings.colorQuadTree);
        Rectangle boundingRect = quadTree.boundingRect;
        shapeRenderer.rect(boundingRect);
        if (quadTree.isSplitted) {
            drawQuadTree(quadTree.ne);
            drawQuadTree(quadTree.nw);
            drawQuadTree(quadTree.se);
            drawQuadTree(quadTree.sw);
        }
    }


    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
