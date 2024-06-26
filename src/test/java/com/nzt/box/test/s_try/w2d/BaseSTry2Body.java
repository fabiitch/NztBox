package com.nzt.box.test.s_try.w2d;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.contact.data.ContactFixture;
import com.nzt.box.contact.listener.ContactListener;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.shape.PolygonShape;
import com.nzt.box.shape.RectangleShape;
import com.nzt.box.test.s_try.base.BaseBox2DSTry;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.debug.hud.core.HudDebug;
import com.nzt.gdx.mains.FastTesterMain;

public abstract class BaseSTry2Body<S1 extends BodyShape, S2 extends BodyShape> extends BaseBox2DSTry {

    protected Body body1, body2, bodyMove, bodyStatic;
    protected String shape1, shape2;

    protected Vector2 posBodyA = new Vector2(-200, 0);
    protected Vector2 posBodyB = new Vector2(200, 0);
    protected boolean contact = false;

    public BaseSTry2Body(FastTesterMain main, BodyType bodyTypeA, BodyType bodyTypeB) {
        super(main);
        body1 = new Body(bodyTypeA);
        Fixture fixture1 = new Fixture(createBodyShape1());
        body1.addFixture(fixture1);
        body1.restitution = 1;
        body1.userData = "B1";
        fixture1.userData = "F1";
        world.addBody(body1);
        body1.setPosition(posBodyA);

        body2 = new Body(bodyTypeB);
        body2.restitution = 1;
        Fixture fixture2 = new Fixture(createBodyShape2());
        fixture2.userData = "F2";
        body2.addFixture(fixture2);
        body2.userData = "B2";
        world.addBody(body2);
        body2.setPosition(posBodyB);
        debugMsg("Collision", false, HudDebugPosition.BOT_RIGHT, Color.BLUE);

        ContactListener contactListener = new ContactListener() {
            @Override
            public void beginContact(ContactFixture contactBody) {
                contact = true;
                HudDebug.update("Collision", true, Color.RED);
            }

            @Override
            public void endContact(ContactFixture contactBody) {
                contact = false;
                HudDebug.update("Collision", false, Color.BLUE);
            }

            @Override
            public void continueContact(ContactFixture contactBody) {

            }

            @Override
            public void preSolve(ContactFixture contactBody) {

            }
        };
        world.setContactListener(contactListener);

        bodyMove = body1;
        bodyStatic = body2;
        shape1 = body1.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();
        shape2 = body2.fixtures.get(0).bodyShape.shape.getClass().getSimpleName();

        if (shape1.equals(shape2)) {
            shape1 += "A";
            shape2 += "B";
        }
        addInfoTestMsg();
        addInputProcessor(addInputListener());
    }

    protected abstract InputProcessor addInputListener();

    protected abstract S1 createBodyShape1();

    protected abstract S2 createBodyShape2();

    private Vector2 tmp = new Vector2();

    @Override
    public void doRender(float dt) {
        debugMsg("Body " + shape2, body2.position);
        debugMsg("Body " + shape1, body1.position);

        debugMsg("Shape" + shape2, body2.fixtures.get(0).bodyShape.getPosition(tmp), HudDebugPosition.BOT_MIDDLE);
        debugMsg("Shape" + shape1, body1.fixtures.get(0).bodyShape.getPosition(tmp), HudDebugPosition.BOT_MIDDLE);
    }


    protected CircleShape createCircle(float radius) {
        return new CircleShape(new Circle(0, 0, radius));
    }

    protected PolygonShape createPolygon1() {
        float[] vertices = new float[]{0, 0, 50, 50, 100, 0, 0, -50};
        return new PolygonShape(new Polygon(vertices));
    }

    protected RectangleShape createRectangle(float witdh, float height) {
        Rectangle rectangle = new Rectangle(0, 0, witdh, height);
        return new RectangleShape(rectangle);
    }

}
