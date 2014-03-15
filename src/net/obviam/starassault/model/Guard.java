package net.obviam.starassault.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Guard {
	public static final float SIZE = 0.7f; //half a unit
	
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2(0,1); //For now, just go up and down
	Rectangle bounds = new Rectangle();
	
	List<Float> yVelocities = new ArrayList<Float>();
	int curVelocityMarker = 0;
	
	float speedModifier = 1f;

	private Vector2 initialPosition;

	public Guard(Vector2 position){
		this(position,1);
	}
	
	public Guard(Vector2 position, int timeOnOneWayPatrolModifier){
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		for(int i = 0; i < timeOnOneWayPatrolModifier * 5; i++){
			yVelocities.add(1f);
		}
		for(int i = 0; i < timeOnOneWayPatrolModifier; i++){
			yVelocities.add(0.5f);
		}
		for(int i = 0; i < timeOnOneWayPatrolModifier; i++){
			yVelocities.add(-0.5f);
		}
		for(int i = 0; i < timeOnOneWayPatrolModifier * 5; i++){
			yVelocities.add(-1f);
		}
		for(int i = 0; i < timeOnOneWayPatrolModifier; i++){
			yVelocities.add(-0.5f);
		}
		for(int i = 0; i < timeOnOneWayPatrolModifier; i++){
			yVelocities.add(0.5f);
		}
	}
	
	public Guard(Vector2 position, int timeOnOneWayPatrolModifier, float speedModifier){
		this(position, timeOnOneWayPatrolModifier);
		initialPosition = position;
		this.speedModifier = speedModifier;
	}
	
	public Vector2 getInitialPosition(){
		return initialPosition;
	}
	
	public Vector2 getPosition(){
		return position;
	}

	public void update(float delta) {
		position.add(velocity.tmp().mul(delta));
		velocity.y = yVelocities.get(++curVelocityMarker % yVelocities.size()) * speedModifier;
	}
}
