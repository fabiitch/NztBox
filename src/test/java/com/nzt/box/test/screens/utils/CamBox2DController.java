package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.math.vectors.V3;

public class CamBox2DController extends InputAdapter {
    private Vector2 cameraVelocity = new Vector2();
    private OrthographicCamera camera;

    public float baseVelocity = 10;

    public CamBox2DController(OrthographicCamera camera) {
        this.camera = camera;
    }

    private final Vector2 tmpVel = new Vector2();

    public void updateCameraPosition() {
        V3.add(camera.position, tmpVel.set(cameraVelocity).scl(camera.zoom));
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            cameraVelocity.add(0, baseVelocity);
        } else if (keycode == Input.Keys.DOWN) {
            cameraVelocity.add(0, -baseVelocity);
        } else if (keycode == Input.Keys.RIGHT) {
            cameraVelocity.add(baseVelocity, 0);
        } else if (keycode == Input.Keys.LEFT) {
            cameraVelocity.add(-baseVelocity, 0);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            cameraVelocity.add(0, -baseVelocity);
        } else if (keycode == Input.Keys.DOWN) {
            cameraVelocity.add(0, baseVelocity);
        } else if (keycode == Input.Keys.RIGHT) {
            cameraVelocity.add(-baseVelocity, 0);
        } else if (keycode == Input.Keys.LEFT) {
            cameraVelocity.add(baseVelocity, 0);
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY > 0) {
            if (camera.zoom < 1)
                camera.zoom = 1;
            else
                camera.zoom += 1;
        } else {
            if (camera.zoom > 1)
                camera.zoom -= 1;
            else
                camera.zoom /= 2;
        }
        return false;
    }
}
