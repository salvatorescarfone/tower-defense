package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Main app that contains graphics elements for the game: from design, camera, background
 * and more.
 *
 */

public class MainGame extends Game {
		SpriteBatch batch;
		BitmapFont font;
		Graphics.DisplayMode display;
		/*Create a MainGame*/
		public void create() {
			batch = new SpriteBatch();
			// Use LibGDX's default Arial font.
			font = new BitmapFont();
			//Get current Graphics.DisplayMode
			display = Gdx.graphics.getDisplayMode();
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
