package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowSizeLimits(500,500,MainGame.WINDOW_WIDTH,MainGame.WINDOW_HEIGHT);
		config.setTitle("Tower Defender");
		new Lwjgl3Application(new MainGame(),config);
	}
}
