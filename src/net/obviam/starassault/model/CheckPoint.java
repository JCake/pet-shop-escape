package net.obviam.starassault.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CheckPoint {
	
	public static final float SIZE = 0.35f; //half a unit
	
	private Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();

	public CheckPoint(Vector2 position){
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
}
