package net.obviam.starassault.controller;

import java.util.HashMap;
import java.util.Map;

import net.obviam.starassault.PetShopEscape;
import net.obviam.starassault.model.Block;
import net.obviam.starassault.model.CheckPoint;
import net.obviam.starassault.model.Guard;
import net.obviam.starassault.model.Turtle;
import net.obviam.starassault.model.Turtle.State;
import net.obviam.starassault.model.World;
import net.obviam.starassault.view.WorldRenderer;

import com.badlogic.gdx.math.Rectangle;

public class WorldController {

	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}
	
	private Turtle turtle;
	private World world;
	private CheckPoint lastCheckPoint;
	private PetShopEscape overallGame;
	
	static Map<Keys,Boolean> keys = new HashMap<WorldController.Keys,Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};
	
	public WorldController(World world, PetShopEscape game){
		this.world = world;
		this.overallGame = game;
		this.turtle = world.getTurtle();
		this.lastCheckPoint = world.getFirstCheckPoint();
	}
	
	public void leftPressed(){
		keys.get(keys.put(Keys.LEFT, true));
	}
	
	public void rightPressed(){
		keys.get(keys.put(Keys.RIGHT, true));
	}
	
	public void downPressed(){
		keys.get(keys.put(Keys.DOWN, true));
	}
	
	public void upPressed(){
		keys.get(keys.put(Keys.UP, true));
	}
	
	public void leftReleased(){
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightReleased(){
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void downReleased(){
		keys.get(keys.put(Keys.DOWN, false));
	}
	
	public void upReleased(){
		keys.get(keys.put(Keys.UP, false));
	}
	
	public void update(float delta){
		processInput();
		turtle.update(delta);
		for(Guard guard : world.getGuards()){
			guard.update(delta);
		}
		if(turtle.getPosition().y > WorldRenderer.CAMERA_HEIGHT){
			overallGame.win();
		}
	}
	
	private void processInput(){
		if(keys.get(Keys.LEFT)){
			turtle.setState(State.WALKING);
			turtle.setFacingLeft(true);
			turtle.getVelocity().x = -Turtle.SPEED;
		}
		else if(keys.get(Keys.RIGHT)){
			turtle.setState(State.WALKING);
			turtle.setFacingLeft(false);
			turtle.getVelocity().x = Turtle.SPEED;
		}
		else{
			turtle.setFacingLeft(null);
			turtle.getAcceleration().x = 0;
			turtle.getVelocity().x = 0;
		}
		if(keys.get(Keys.UP)){
			turtle.setState(State.WALKING);
			turtle.setFacingUp(true);
			turtle.getVelocity().y = Turtle.SPEED;
		}
		else if(keys.get(Keys.DOWN)){
			turtle.setState(State.WALKING);
			turtle.setFacingUp(false);
			turtle.getVelocity().y = -Turtle.SPEED;
		}
		else{
			turtle.setFacingUp(null);
			turtle.getAcceleration().y = 0;
			turtle.getVelocity().y = 0;
		}
		handleCollisions();
	}
	
	private void handleCollisions(){
		Rectangle bobBox = new Rectangle(turtle.getPosition().x, turtle.getPosition().y, Turtle.SIZE, Turtle.SIZE);
		
		for(CheckPoint checkPoint : world.getCheckPoints()){
			Rectangle checkPointBox = new Rectangle(checkPoint.getPosition().x, checkPoint.getPosition().y, 
					CheckPoint.SIZE, CheckPoint.SIZE);
			if(bobBox.overlaps(checkPointBox)){
				this.lastCheckPoint = checkPoint;
			}
		}
		
		for(Guard guard : world.getGuards()){
			Rectangle guardBox = new Rectangle(guard.getPosition().x, guard.getPosition().y, Guard.SIZE, Guard.SIZE);
			if(bobBox.overlaps(guardBox)){
				System.out.println("YOU LOSE!");
				turtle.getPosition().x = lastCheckPoint.getPosition().x;
				turtle.getPosition().y = lastCheckPoint.getPosition().y;
				return;
			}
		}
		
		
		float margin = 0.01f;
		for(Block block : world.getBlocks()){
			Rectangle blockBox = new Rectangle(block.getPosition().x, block.getPosition().y, Block.SIZE, Block.SIZE);
			if(bobBox.overlaps(blockBox)){
				if(turtle.getFacingLeft() != null){
					if(turtle.getFacingLeft() && leftOf(blockBox,bobBox)){
						turtle.getVelocity().x = 0;
						turtle.getPosition().x = Math.max(turtle.getPosition().x, block.getPosition().x + Block.SIZE + margin);
					}else if(!turtle.getFacingLeft() && leftOf(bobBox, blockBox)){
						turtle.getVelocity().x = 0;
						turtle.getPosition().x = Math.min(turtle.getPosition().x, block.getPosition().x - Turtle.SIZE - margin);
					}
				}
				if(turtle.getFacingUp() != null){
					if(turtle.getFacingUp() && above(blockBox,bobBox)){
						turtle.getVelocity().y = 0;
						turtle.getPosition().y = Math.min(turtle.getPosition().y,block.getPosition().y - Turtle.SIZE - margin);
					}else if(!turtle.getFacingUp() && above(bobBox,blockBox)){
						turtle.getVelocity().y = 0;
						turtle.getPosition().y = Math.max(turtle.getPosition().y, block.getPosition().y + Block.SIZE + margin);
					}
				}
			}
		}
	}

	private boolean leftOf(Rectangle oneRect, Rectangle otherRect) {
		return oneRect.getX() < otherRect.getX() && oneRect.getX() + oneRect.getWidth() < otherRect.getX() + 0.1;
	}
	
	private boolean above(Rectangle oneRect, Rectangle otherRect) {
		return oneRect.getY() > otherRect.getY() && oneRect.getY() + 0.1 > otherRect.getY() + otherRect.getHeight();
	}
	
}
