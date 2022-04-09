package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MainGame extends Game {
	private Music music;
	private int score = 0;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private ExtendViewport viewport;
	private float width;
	private float height;
	private boolean musicOn = true;
	private static Game instance;
	private GameState runningState;
	private GameState pauseState;
	private GameState currentState;
	private long pauseTime;
	private boolean paused;

	public long getPauseTime() {
		return pauseTime;
	}
	public void setPauseTime(long pauseTime) {
		this.pauseTime = pauseTime;
	}
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public GameState getRunningState() {
		return runningState;
	}
	public void setRunningState(GameState runningState) {
		this.runningState = runningState;
	}
	public GameState getPauseState() {
		return pauseState;
	}
	public void setPauseState(GameState pauseState) {
		this.pauseState = pauseState;
	}
	public GameState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public SpriteBatch getBatch() {
		return batch;
	}
	public BitmapFont getFont() {
		return font;
	}
	public OrthographicCamera getCamera() {
		return camera;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public boolean isMusicOn() {
		return musicOn;
	}
	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}

	public void create() {
		paused=false;
		pauseTime=0;
		runningState = new RunningState();
		pauseState = new PauseState();
		currentState = runningState;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,width, height);
		viewport = new ExtendViewport(width, height,camera);
		music = Gdx.audio.newMusic(Gdx.files.internal("Music/menu.mp3"));
		setScreen(new MainMenuScreen());
	}
	private MainGame(){
		super();
	}
	public static Game getInstance(){
		if (instance == null){
			instance= new MainGame();
		}
		return instance;
	}
	public void render() {
		/* Without this call, the Screen that you set in the create()
		 * method will not be rendered if you override the render method in your Game class!
		 */
		super.render();
	}
	public void dispose() {
		batch.dispose();
		font.dispose();
		music.dispose();
	}
	public void resize(int width, int height){
		viewport.update(width,height,true);
		camera.setToOrtho(false,width,height);
		batch.setProjectionMatrix(camera.combined);
		this.width = width;
		this.height = height;
	}
}
