package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Main app that contains graphics elements for the game: from design, camera, background
 * and more.
 *
 */

public class MainGame extends Game {
		SpriteBatch batch;
		BitmapFont font;
		/*Values of game window: The window isn't resizable*/
		public final static int WINDOW_WIDTH=1280;
		public final static int WINDOW_HEIGHT=675;
		/*Create a MainGame*/
		public void create() {
			batch = new SpriteBatch();
			// Use LibGDX's default Arial font.
			font = new BitmapFont();
			//Set The First Screen to MainMenu!
			this.setScreen(new MainMenuScreen(this));
		}

		public void render() {
			/* Without this call, the Screen that you set in the create()
			 * method will not be rendered if you override the render method in your Game class!
			 */
			super.render(); // important!
		}
		//Disposing of heavy objects
		public void dispose() {
			batch.dispose();
			font.dispose();
		}
}
