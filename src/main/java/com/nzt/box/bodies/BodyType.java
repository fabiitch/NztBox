package com.nzt.box.bodies;

public enum BodyType {

    Static, Dynamic, Kinematic, Ghost;

    /**
     * Static cant move
     * Dynamic move | collide
     * Kinematic, no gravity, no collision with Static
     * Ghost no collision
     */
}
