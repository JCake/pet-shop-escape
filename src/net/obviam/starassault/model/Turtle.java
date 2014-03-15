package net.obviam.starassault.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Turtle {

	public enum State {
		IDLE, WALKING
	}
	
	public static final float SPEED = 2f; //unit per second
	public static final float SIZE = 0.7f; //half a unit
	
	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state = State.IDLE;
	Boolean facingLeft = true;
	Boolean facingUp = true;

	public Turtle(Vector2 position){
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setFacingLeft(Boolean b) {
		this.facingLeft = b;
	}
	
	public void setFacingUp(Boolean b){
		this.facingUp = b;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public State getState(){
		return state;
	}
	
	public void update(float delta){
		position.add(velocity.tmp().mul(delta));
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Boolean getFacingLeft() {
		return facingLeft;
	}
	
	public Boolean getFacingUp() {
		return facingUp;
	}
}
