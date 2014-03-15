package net.obviam.starassault.model;

import java.util.ArrayList;
import java.util.List;

import net.obviam.starassault.view.WorldRenderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {
	
	List<Block> blocks = new ArrayList<Block>();
	Array<Guard> guards = new Array<Guard>();
	Turtle turtle;
	
	public List<Block> getBlocks(){
		return blocks;
	}
	public Array<Guard> getGuards(){
		return guards;
	}
	public Turtle getTurtle(){
		return turtle;
	}
	
	public World(){
		createDemoWorld();
	}
	private void createDemoWorld() {
		turtle = new Turtle(new Vector2(10,2));
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
	public void reset() {
		createDemoWorld();
	}


}
