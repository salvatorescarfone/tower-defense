package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Tower Sage");
		config.setWindowedMode(1280,680);
		config.setResizable(false);
		config.setWindowIcon("GameScreen/game_icon.png");
		new Lwjgl3Application(MainGame.getInstance(),config);
	}
}

