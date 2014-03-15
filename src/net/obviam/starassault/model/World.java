package net.obviam.starassault.model;

import java.util.ArrayList;
import java.util.List;

import net.obviam.starassault.view.WorldRenderer;

import com.badlogic.gdx.math.Vector2;

public class World {
	
	List<Block> blocks = new ArrayList<Block>();
	List<CheckPoint> checkPoints = new ArrayList<CheckPoint>();
	List<Guard> guards = new ArrayList<Guard>();
	Turtle turtle;
	private CheckPoint firstCheckPoint;
	
	public List<Block> getBlocks(){
		return blocks;
	}
	public List<Guard> getGuards(){
		return guards;
	}
	public Turtle getTurtle(){
		return turtle;
	}
	public List<CheckPoint> getCheckPoints(){
		return checkPoints;
	}
	
	public World(){
		createDemoWorld();
	}
	private void createDemoWorld() {
		turtle = new Turtle(new Vector2(10,2));
		checkPoints.add(new CheckPoint(new Vector2(10,2)));
		checkPoints.add(new CheckPoint(new Vector2(11.3f,8f)));
		firstCheckPoint = checkPoints.get(0);
		
		guards.add(new Guard(new Vector2(7.25f,1),17,1.35f));
		guards.add(new Guard(new Vector2(5,6.15f),9,0.9f));
		guards.add(new Guard(new Vector2(8.1f,9.15f),9,2f));
		
		for(int i = 0; i < WorldRenderer.CAMERA_WIDTH; i++){
			blocks.add(new Block(new Vector2(i,0)));
			if(i != 5){
				blocks.add(new Block(new Vector2(i,WorldRenderer.CAMERA_WIDTH - 1)));
			}
		}
		
		for(int i = 1; i < WorldRenderer.CAMERA_HEIGHT - 1; i++){
			blocks.add(new Block(new Vector2(0,i)));
			blocks.add(new Block(new Vector2(WorldRenderer.CAMERA_HEIGHT - 1,i)));
		}
		
		for(int i = 0; i < WorldRenderer.CAMERA_WIDTH; i++){
			if(i != 7){
				blocks.add(new Block(new Vector2(i,3)));
			}
			if(i != 2){
				blocks.add(new Block(new Vector2(i,5)));
			}
			if(i != 11){
				blocks.add(new Block(new Vector2(i,8)));
			}
			if(i != 4 && i != 8){
				blocks.add(new Block(new Vector2(i,10)));
			}
			if(i != 12){
				blocks.add(new Block(new Vector2(i,12)));
			}
		}

	}

	public CheckPoint getFirstCheckPoint() {
		return firstCheckPoint;
	}


}
