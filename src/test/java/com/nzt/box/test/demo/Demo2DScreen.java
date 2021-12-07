package com.nzt.box.test.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.debug.render.g2d.Box2DDebugRenderer;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.s_try.base.RectangleWalls;
import com.nzt.box.world.World;
import com.nzt.gdx.test.utils.GdxTestUtils;

public class Demo2DScreen extends ApplicationAdapter {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Demo 2D Screen");
        configuration.setWindowedMode(800, 400);
        configuration.setWindowIcon("box.png");
        configuration.useVsync(true);
        new Lwjgl3Application(new Demo2DScreen(), configuration);
    }

    World world = new World();
    Box2DDebugRenderer box2DDebugRenderer;
    OrthographicCamera camera;

    @Override
    public void create() {
        world = new World();
        box2DDebugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        new RectangleWalls(GdxTestUtils.screenAsRectangle(camera, true),
                200, world);

        for (int i = 0; i < 10; i++) {
            Body body = new Body(BodyType.Dynamic);
            BodyShape shape = new CircleShape(10);
            Fixture<?> fixture = new Fixture<>(shape);
            body.addFixture(fixture);
            world.addBody(body);

            Vector2 pos = new Vector2();
            pos.x = MathUtils.random(10, 390);
            pos.y = MathUtils.random(10, 800);
            body.setPosition(pos);

            Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
            body.setVelocity(velocity);
        }

        super.create();
    }


    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(Color.BLACK);

        camera.update();
        world.step(dt);
        box2DDebugRenderer.render(world, camera.combined);
    }
}
