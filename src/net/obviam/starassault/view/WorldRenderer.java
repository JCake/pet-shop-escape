package net.obviam.starassault.view;

import java.util.ArrayList;
import java.util.List;

import net.obviam.starassault.model.Block;
import net.obviam.starassault.model.Guard;
import net.obviam.starassault.model.Turtle;
import net.obviam.starassault.model.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {
	
	public static final int CAMERA_WIDTH = 15;
	public static final int CAMERA_HEIGHT = 15;
	
	private OrthographicCamera cam;
	private World world;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	private Texture walkTexture;
	private Texture turtleStillTexture;
	
	private Texture walkTextureRight;
	private Texture turtleStillTextureRight;
	
	private Texture walkTextureLeft;
	private Texture turtleStillTextureLeft;
	
	private List<Texture> blockTexture = new ArrayList<Texture>();
	private Texture guardTexture;
	private Texture checkPointTexture;
	private Texture doorTexture;
	
	private SpriteBatch spriteBatch;
	
	private int width;
	private int height;
	private float ppuX ; //pixels per unit on X axis
	private float ppuY; //pixels per unit on Y axis
	public void setSize(int w, int h){
		System.out.println("setting size: " + w + "," + h);
		this.width = w;
		this.height = h;
		ppuX = (float) width / (float) CAMERA_WIDTH;
		ppuY = (float) height / (float) CAMERA_HEIGHT;
	}
	
	
	public WorldRenderer(World world){
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f,CAMERA_HEIGHT/2f,0);
		this.cam.update();
		spriteBatch = new SpriteBatch();
		loadTextures();
		for(Guard guard : world.getGuards()){
			guard.getPosition().x = guard.getInitialPosition().x;
			guard.getPosition().y = guard.getInitialPosition().y;
			System.out.println("GUARD Y: " + guard.getInitialPosition().y);
		}
		tedwardPreviousXPos = world.getTurtle().getPosition().x;
	}
	
	List<Integer> randomIndices = new ArrayList<Integer>();
	
	private void loadTextures(){
		walkTextureRight = new Texture(Gdx.files.internal("images/tedward.png"));
		turtleStillTextureRight = new Texture(Gdx.files.internal("images/tedwardStill.png"));
		walkTextureLeft = new Texture(Gdx.files.internal("images/tedwardFacingLeft.png"));
		turtleStillTextureLeft = new Texture(Gdx.files.internal("images/tedwardStillFacingLeft.png"));
		
		walkTexture = walkTextureLeft;
		turtleStillTexture = turtleStillTextureLeft;
		
		blockTexture.add(new Texture(Gdx.files.internal("images/hamsters.png")));
		blockTexture.add(new Texture(Gdx.files.internal("images/snakes.png")));
		blockTexture.add(new Texture(Gdx.files.internal("images/puppy.png")));
		for(int i = 0; i < world.getBlocks().size(); i++){
			randomIndices.add((int)(Math.random() * 3));
		}
		guardTexture = new Texture(Gdx.files.internal("images/catGuard.png"));
		checkPointTexture = new Texture(Gdx.files.internal("images/appleCheckpoint.png"));
		doorTexture = new Texture(Gdx.files.internal("images/door.png"));
	}
	
	public void render(){
		spriteBatch.begin();
		drawBlocks();
		drawCheckpoints();
		drawGuards();
		drawTedward();
		drawDoor();
		spriteBatch.end();
//		if(debug)
//			drawDebug();
	}
	
	private void drawCheckpoints(){
		spriteBatch.draw(checkPointTexture, 10 * ppuX, 2 * ppuY, 
				Turtle.SIZE * ppuX / 2,Turtle.SIZE * ppuY /2);	
	}
	
	private void drawDoor(){
		spriteBatch.draw(doorTexture, 5 * ppuX, 14 * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
	}
	
	private void drawBlocks(){
		for(int i = 0; i < world.getBlocks().size(); i++){
			Block block = world.getBlocks().get(i);
			spriteBatch.draw(blockTexture.get(randomIndices.get(i)), block.getPosition().x * ppuX, block.getPosition().y * ppuY, 
					Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
	}
	
	private void drawGuards(){
		for(Guard guard : world.getGuards()){
			spriteBatch.draw(guardTexture, guard.getPosition().x * ppuX, guard.getPosition().y * ppuY, 
					Guard.SIZE * ppuX, Guard.SIZE * ppuY);
		}
	}
	
	private boolean turtleWasStill = true;
	private float tedwardPreviousXPos;
	
	private void drawTedward(){
		Turtle bob = world.getTurtle();
		if(bob.getFacingLeft() != null && bob.getFacingLeft()){
			walkTexture = walkTextureLeft;
			turtleStillTexture = turtleStillTextureLeft;
		}else if(bob.getFacingLeft() != null && !bob.getFacingLeft()){
			walkTexture = walkTextureRight;
			turtleStillTexture = turtleStillTextureRight;
		}
		if(Math.abs(tedwardPreviousXPos - bob.getPosition().x) > 0.5){
			if(turtleWasStill){
				spriteBatch.draw(walkTexture, bob.getPosition().x * ppuX, bob.getPosition().y * ppuY, 
						Turtle.SIZE * ppuX, Turtle.SIZE * ppuY);
				turtleWasStill = false;
			}
			else{
				spriteBatch.draw(turtleStillTexture, bob.getPosition().x * ppuX, bob.getPosition().y * ppuY, 
						Turtle.SIZE * ppuX, Turtle.SIZE * ppuY);
				turtleWasStill = true;
			}
			tedwardPreviousXPos = bob.getPosition().x;
		}else{
			if(turtleWasStill){
				spriteBatch.draw(turtleStillTexture, bob.getPosition().x * ppuX, bob.getPosition().y * ppuY, 
						Turtle.SIZE * ppuX, Turtle.SIZE * ppuY);
			}
			else{
				spriteBatch.draw(walkTexture, bob.getPosition().x * ppuX, bob.getPosition().y * ppuY, 
						Turtle.SIZE * ppuX, Turtle.SIZE * ppuY);
			}
		}
	}
	
	
	public void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);
		for(Block block : world.getBlocks()){
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(block.getColor());
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}
		
		Turtle bob = world.getTurtle();
		Rectangle rect = bob.getBounds();
		float x1 = bob.getPosition().x + rect.x;
		float y1 = bob.getPosition().y + rect.y;
		
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
		
	}

}
