package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


/* This is the First Screen that the User is met with. It's the MainMenuScreen. In here are rendered all
 * graphics for the main menu and the user is met with instructions on how to start the game (click
 * anywhere to start).
 */

public class MainMenuScreen implements Screen {
    final MainGame game;
    OrthographicCamera camera;
    /*Constructor for the MainMenu*/
    public MainMenuScreen(final MainGame game){
        this.game=game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,game.display.width,game.display.height);

    }
    /*Method called when this Screen becomes the current screen for a game*/
    @Override
    public void show() {

    }
    /*Renders all the graphics for the main Menu*/
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Tower Defence, click or tap anywhere to begin!", game.display.width/2, game.display.height/2);
        game.batch.end();
        //If mouse is pressed or Screen is touched: set new GameScreen, dispose of this Screen
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
    /*Called when the Screen is resized.*/
    @Override
    public void resize(int width, int height) {
    }
    /*Called when the Screen is paused, usually when it's not visible*/
    @Override
    public void pause() {

    }
    /*Called to resume a paused Screen*/
    @Override
    public void resume() {

    }
    /*Called when this screen is no longer the current screen for a Game*/
    @Override
    public void hide() {

    }
    /*Called when this screen should release all resources*/
    @Override
    public void dispose() {

    }
}
