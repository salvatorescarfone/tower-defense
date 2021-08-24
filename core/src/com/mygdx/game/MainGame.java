package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/* Main app that contains graphics elements for the game: from design, camera, background
 * and more.
 *
 */
public class MainGame extends Game {
		Music music;
		int score = 0;			//Total Score of the game in process
		SpriteBatch batch;
		BitmapFont font;
		ShapeRenderer shapeRenderer;
		OrthographicCamera camera;
		ExtendViewport viewport;
		Graphics.DisplayMode display;
		float width;
		float height;
		/*Create a MainGame*/
		public void create() {
			batch = new SpriteBatch();
			shapeRenderer = new ShapeRenderer();
			display = Gdx.graphics.getDisplayMode();
			Gdx.graphics.setFullscreenMode(display);
			width= display.width;
			height=display.height;
			// Use LibGDX's default Arial font.
			font = new BitmapFont();
			camera = new OrthographicCamera();
			camera.setToOrtho(false,width, height);
			viewport = new ExtendViewport(width, height,camera);
			//music = Gdx.audio.newMusic(Gdx.files.internal(""));
			//music.play();
			//music.setLooping(true);
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
			//music.dispose();
			shapeRenderer.dispose();
		}
		public void resize(int width, int height){
			viewport.update(width,height,true);
			camera.setToOrtho(false,width,height);
			batch.setProjectionMatrix(camera.combined);
		}
}
