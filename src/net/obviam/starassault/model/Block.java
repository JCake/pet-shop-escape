package net.obviam.starassault.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
	
	public static final float SIZE = 1f;
	
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	public boolean pressed = false;
	int colorIndex = 0;
	public Color[] colors = new Color[]{Color.BLUE, Color.GREEN, Color.RED};
	
	public Block(Vector2 pos){
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Color getColor() {
		return colors[colorIndex % colors.length];
	}
	
	public void switchColor(){
		colorIndex++;
	}

}
