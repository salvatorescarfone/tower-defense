package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

/* This is the First Screen that the User is met with. It's the MainMenuScreen. In here are rendered all
 * graphics for the main menu and the user is met with instructions on how to start the game (click
 * anywhere to start).
 */

public class MainMenuScreen implements Screen {
    private MainGame game;
    private AbstractGameObjectFactory creator;
    private MyButton optionsButton;
    private Texture background;
    private Hero hero;
    private Enemy enemy;
    private Tower tower;
    private Texture title;
    private GlyphLayout startText;

    /*Constructor for the MainMenu*/
    public MainMenuScreen(){
        this.game=(MainGame)MainGame.getInstance();
        creator = new GameObjectFactory();
        optionsButton = creator.createButton("options");
        startText= creator.createText( "Welcome to Tower Defense, click anywhere to begin!\n" +
                "                                  Press Q to exit", "black");
        tower = creator.createTower();
        hero = creator.createHero();
        enemy = creator.createEnemy();
        title = new Texture("backgrounds/MainMenuTitle.png");
        background = new Texture("backgrounds/main_menu_background.png");

    }
    @Override
    public void show() {
        game.getMusic().setVolume(0.2f);
        if(!game.getMusic().isPlaying() && game.isMusicOn()){
            game.getMusic().play();
            game.getMusic().setLooping(true);
        }
    }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getCamera().update();
        game.getBatch().begin();

        game.getBatch().draw(background,0,0, game.getWidth(), game.getHeight());
        game.getBatch().draw(tower.img,tower.hitBox.x,tower.hitBox.y,tower.hitBox.width,tower.hitBox.height);
        game.getBatch().draw(title, game.getWidth() /2f - title.getWidth()/2f, game.getHeight() /2f - title.getHeight()/2f, title.getWidth(),title.getHeight());
        game.getFont().draw(game.getBatch(),startText,(game.getWidth() /2f -  startText.width / 2), (game.getHeight() - startText.height / 3));

        hero.act(game.getBatch());
        enemy.act(game.getBatch(), 11f,tower.hitBox);
        optionsButton.act(game.getBatch());
        if(!optionsButton.isActive() && Gdx.input.justTouched()){
            game.getMusic().stop();
            game.setScreen(game.getGameScreen());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }
        game.getBatch().end();
    }

    @Override
    public void dispose() {
        optionsButton.dispose();
        title.dispose();
        background.dispose();
        tower.dispose();
        enemy.dispose();
        hero.dispose();
    }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() {
    }
}
