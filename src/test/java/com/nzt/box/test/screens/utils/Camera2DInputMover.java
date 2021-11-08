package com.nzt.box.test.screens.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.nzt.gdx.math.vectors.V3;

public class Camera2DInputMover extends InputAdapter {
    private Vector2 cameraVelocity = new Vector2();
    private OrthographicCamera camera;

    public float baseVelocity = 10;
    public Camera2DInputMover(OrthographicCamera camera) {
        this.camera = camera;
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
        V3.add(camera.position, cameraVelocity.cpy().scl(camera.zoom));
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
        V3.add(camera.position, cameraVelocity.cpy().scl(camera.zoom));
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom += amountY * 2;
        return false;
    }
}
