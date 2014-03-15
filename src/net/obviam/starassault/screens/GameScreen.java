package net.obviam.starassault.screens;

import net.obviam.starassault.PetShopEscape;
import net.obviam.starassault.controller.WorldController;
import net.obviam.starassault.model.World;
import net.obviam.starassault.view.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GameScreen implements Screen, InputProcessor {
	
	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	private PetShopEscape game;
	
	public GameScreen(PetShopEscape game){
		this.game = game;
	}
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255.0f, 255.0f, 255.0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		controller.update(delta); //IMPORTANT!
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world,game);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.LEFT)
			controller.leftPressed();
		if(keycode == Keys.RIGHT)
			controller.rightPressed();
		if(keycode == Keys.UP)
			controller.upPressed();
		if(keycode == Keys.DOWN)
			controller.downPressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT)
			controller.leftReleased();
		if(keycode == Keys.RIGHT)
			controller.rightReleased();
		if(keycode == Keys.UP)
			controller.upReleased();
		if(keycode == Keys.DOWN)
			controller.downReleased();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
