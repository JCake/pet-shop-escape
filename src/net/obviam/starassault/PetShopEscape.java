package net.obviam.starassault;

import net.obviam.starassault.screens.GameScreen;
import net.obviam.starassault.screens.WinScreen;

import com.badlogic.gdx.Game;

public class PetShopEscape extends Game {

	private GameScreen screen;
	private WinScreen winScreen;

	@Override
	public void create() {
		screen = new GameScreen(this);
		winScreen = new WinScreen();
		setScreen(screen);
		
	}

	public void win() {
		setScreen(winScreen);
	}
}
