package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

/* This is the First Screen that the User is met with. It's the MainMenuScreen. In here are rendered all
 * graphics for the main menu and the user is met with instructions on how to start the game (click
 * anywhere to start).
 */

public class MainMenuScreen implements Screen {
    final MainGame GAME;
    MyButton optionsButton;
    Texture background;
    Hero hero;
    Enemy enemy;
    Tower tower;
    Texture title;

    private static final float OPTION_BUTTON_WIDTH=50f;
    private static final float OPTION_BUTTON_HEIGHT=49f;
    //Used to display click to Start on Screen
    final GlyphLayout startText;

    /*Constructor for the MainMenu*/
    public MainMenuScreen(final MainGame game){
        this.GAME =game;
        optionsButton = new MyButton("Buttons/options_active.png", "Buttons/options_inactive.png",
                GAME.width - OPTION_BUTTON_WIDTH,GAME.height-OPTION_BUTTON_HEIGHT,OPTION_BUTTON_WIDTH,OPTION_BUTTON_HEIGHT, GAME.width,
                GAME.height);
        startText= new GlyphLayout();
        GAME.font.setColor(Color.BLACK);
        startText.setText(GAME.font, "Welcome to Tower Defense, click anywhere to begin!\n" +
                "                                  Press Q to exit");
        tower = new Tower(50f, 15f);
        hero = new Hero((GAME.width/2f) - (57f/2f),6f);
        enemy = new Enemy (MathUtils.random(0,1),true);
        title = new Texture("backgrounds/MainMenuTitle.png");
        background = new Texture("backgrounds/main_menu_background.png");

        GAME.music.setVolume(0.2f);
        if(!GAME.music.isPlaying() && GAME.musicOn){
            GAME.music.play();
            GAME.music.setLooping(true);
        }
    }
    /*Method called when this Screen becomes the current screen for a game*/
    @Override
    public void show() { }
    /*Called when the Screen is resized.*/
    @Override
    public void resize(int width, int height) { }
    /*Renders all the graphics for the main Menu*/
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        GAME.batch.setProjectionMatrix(GAME.camera.combined);
        GAME.camera.update();
        GAME.batch.begin();
        GAME.batch.draw(background,0,0, GAME.width, GAME.height);
        GAME.batch.draw(tower.img,tower.hitBox.x,tower.hitBox.y,tower.hitBox.width,tower.hitBox.height);
        GAME.batch.draw(title, GAME.width/2f - title.getWidth()/2f, GAME.height/2f - title.getHeight()/2f, title.getWidth(),title.getHeight());
        hero.animate(GAME.batch,11f);
        enemy.animate(GAME.batch,11f);

        if (!enemy.hitBox.overlaps(hero.hitBox)){
                enemy.hitBox.x -=enemy.runSpeed;
        }
        else{
            enemy.Idle();
        }
        //Drawing options buttons and input management for accessing menus
        GAME.font.draw(GAME.batch,startText,(GAME.width/2f -  startText.width / 2), (GAME.height - startText.height / 3));
        optionsButton.draw(GAME.batch);
        if (optionsButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            GAME.setScreen(new OptionsScreen(GAME));
        }
        else if(!optionsButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            GAME.music.stop();
            GAME.setScreen(new GameScreen(GAME));
        }

        //Close game if Q is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            this.dispose();
            Gdx.app.exit();
        }

        GAME.batch.end();
    }
    /*Called when this screen should release all resources*/
    @Override
    public void dispose() {
        optionsButton.dispose();
        title.dispose();
        background.dispose();
        tower.dispose();
        enemy.dispose();
        hero.dispose();
    }
    /*Called when the Screen is paused, usually when it's not visible*/
    @Override
    public void pause() { }
    /*Called to resume a paused Screen*/
    @Override
    public void resume() { }
    /*Called when this screen is no longer the current screen for a Game*/
    @Override
    public void hide() { }

}
