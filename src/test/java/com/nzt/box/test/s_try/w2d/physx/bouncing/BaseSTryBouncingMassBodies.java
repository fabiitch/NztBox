package com.nzt.box.test.s_try.w2d.physx.bouncing;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyDef;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.BodyShape;
import com.nzt.box.test.s_try.utils.BoxDebugUtils;
import com.nzt.box.test.s_try.w2d.BaseSTryMultipleBody;
import com.nzt.gdx.debug.hud.HudDebugPosition;
import com.nzt.gdx.input.impl.simple.SimpleClickInputHandler;
import com.nzt.gdx.test.utils.archi.mains.mains.FastTesterMain;
import com.nzt.gdx.test.utils.screen_selector.TestScreen;

@TestScreen(group = "2D.physx.bouncing")
public abstract class BaseSTryBouncingMassBodies extends BaseSTryMultipleBody {
    private Vector2 tmpPos = new Vector2();
    private Rectangle rectScreen;
    private int userData = 1;
    BodyDef bodyDef;

    public BaseSTryBouncingMassBodies(FastTesterMain main) {
        super(main);
        this.bodyDef = bodyDef();
        debugRenderer.debugSettings.drawVelocity = false;
        debugRenderer.debugSettings.drawQuadTree = true;
        debugRenderer.debugSettings.drawQuadTreeData = true;
        createWallAroundScreen();
        rectScreen = new Rectangle(-SCREEN_WITDH / 2, -SCREEN_HEIGHT / 2, SCREEN_WITDH, SCREEN_HEIGHT);
        for (int i = 0; i < 10; i++) {
            createBody();
        }
        BoxDebugUtils.toHud(bodyDef,"Ball", HudDebugPosition.LEFT_MIDDLE);
        BoxDebugUtils.toHud(bodyDef,"Walls", HudDebugPosition.RIGHT_MIDDLE);
        infoMsg("Click to add 10 balls");
        addInputProcessor(new SimpleClickInputHandler() {
            @Override
            public boolean click(int screenX, int screenY, int pointer, int button) {
                for (int i = 0; i < 10; i++) {
                    createBody();
                }
                return false;
            }
        });
    }


    protected abstract BodyDef bodyDef();

    protected abstract BodyShape bodyShape();

    private Body createBody() {
        Body body = new Body(bodyDef);
        BodyShape shape = bodyShape();
        Fixture<?> fixture = new Fixture<>(shape);
        body.addFixture(fixture);
        world.addBody(body);
        body.userData = userData++;
        Vector2 pos = new Vector2();
        pos.x = MathUtils.random(-SCREEN_WITDH / 2 + 3, SCREEN_WITDH / 2 - 1);
        pos.y = MathUtils.random(-SCREEN_HEIGHT / 2 + 3, SCREEN_HEIGHT / 2 - 1);
        body.setPosition(pos);
        Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(150);
        body.setVelocity(velocity);
        return body;
    }

    @Override
    public void doRenderM(float dt) {
        Array<Body> bodiesNew = new Array<>();

        int bodyOut = 0;
        for (int i = 0; i < world.data.bodies.size; i++) {
            Body body = world.data.bodies.get(i);
            body.getPosition(tmpPos);
            if (rectScreen.contains(tmpPos)) {
                bodiesNew.add(body);
            } else {
                bodyOut++;
            }
        }
        debugMsg("Balls in rect", bodiesNew.size);
        debugMsg("Balls out of rect", bodyOut);
        bodiesNew.shrink();
        bodiesNew.clear();
    }

    @Override
    public String getTestExplication() {
        return "Multiple circle collion";
    }

}
