package com.nzt.box.test.screens.physx;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.nzt.box.bodies.Body;
import com.nzt.box.bodies.BodyType;
import com.nzt.box.bodies.Fixture;
import com.nzt.box.shape.CircleShape;
import com.nzt.box.test.screens.base.Box2dTestScreen;
import com.nzt.gdx.test.trials.tester.archi.main.FastTesterMain;
import com.nzt.gdx.test.trials.tester.selector.TestScreenList;

@TestScreenList(group = "2D.physx")
public class DoubleBouncingBall extends Box2dTestScreen {

	public DoubleBouncingBall(FastTesterMain main) {
		super(main);
		createWallAroundScreen();
		Body ball1 = createBall(0);
		Body ball2 = createBall(1);
		ball1.setPosition(-200, 0);
		ball2.setPosition(200, 0);
		
		ball1.setVelocity(100, 0);
		ball2.setVelocity(-100, 0);
		world.addBody(ball1);
		world.addBody(ball2);
	}

	@Override
	public void doRender(float dt) {

	}

	@Override
	public String getTestExplication() {
		// TODO Auto-generated method stub
		return "Test collision balls";
	}

	private Body createBall(int userData) {
		Body body = new Body(BodyType.Dynamic);
		Circle circle = new Circle(0, 0, 10);
		CircleShape shape = new CircleShape(circle);
		Fixture fixture = new Fixture(shape);
		body.addFixture(fixture);
		body.userData = userData;
		Vector2 velocity = new Vector2(1, 0).setToRandomDirection().setLength(250);
		body.setVelocity(velocity);
		return body;
	}
}
