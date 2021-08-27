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
    final MainGame game;
    MyButton optionsButton;
    Texture background;
    Hero hero;
    Enemy enemy;
    Tower tower;
    Texture title;

    //private static final float START_TEXT_HEIGHT=game.height;
    private static final float OPTION_BUTTON_WIDTH=50f;
    private static final float OPTION_BUTTON_HEIGHT=49f;
    private static final float OPTION_BUTTON_Y=0f;
    //Used to display click to Start on Screen
    final GlyphLayout startText;

    /*Constructor for the MainMenu*/
    public MainMenuScreen(final MainGame game){
        this.game=game;
        optionsButton = new MyButton("Buttons/options_active.png", "Buttons/options_inactive.png",
                game.width - OPTION_BUTTON_WIDTH,game.height-OPTION_BUTTON_HEIGHT,OPTION_BUTTON_WIDTH,OPTION_BUTTON_HEIGHT, game.width,
                game.height);
        startText= new GlyphLayout();
        game.font.setColor(Color.BLACK);
        startText.setText(game.font, "Welcome to Tower Defense, click anywhere to begin!\n" +
                "                                  Press Q to exit");
        tower = new Tower(50f, 15f);
        hero = new Hero((game.width/2f) - (57f/2f),6f);
        enemy = new Enemy (MathUtils.random(0,1),true);
        title = new Texture("backgrounds/MainMenuTitle.png");
        background = new Texture("backgrounds/main_menu_background.png");

        if(!game.music.isPlaying() && game.musicOn){
            game.music.play();
            game.music.setLooping(true);
        }
    }
    /*Method called when this Screen becomes the current screen for a game*/
    @Override
    public void show() { }
    /*Called when the Screen is resized.*/
    @Override
    public void resize(int width, int height) {
        game.resize(width,height);
    }
    /*Renders all the graphics for the main Menu*/
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(background,0,0,game.width,game.height);
        game.batch.draw(tower.img,tower.hitBox.x,tower.hitBox.y,tower.hitBox.width,tower.hitBox.height);
        game.batch.draw(title,game.width/2f - title.getWidth()/2f,game.height/2f - title.getHeight()/2f, title.getWidth(),title.getHeight());
        hero.animate(game.batch,11f);
        enemy.animate(game.batch,11f);

        if (!enemy.hitBox.overlaps(hero.hitBox)){
                enemy.hitBox.x -=enemy.runSpeed;
        }
        else{
            enemy.Idle();
        }
        //Drawing options buttons and input management for accessing menus
        game.font.draw(game.batch,startText,(game.width/2f -  startText.width / 2), (game.height - startText.height / 3));
        optionsButton.draw(game.batch);
        if (optionsButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new OptionsScreen(game));
        }
        else if(!optionsButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            game.music.stop();
            game.setScreen(new GameScreen(game));
        }

        //Close game if Q is pressed
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            this.dispose();
            Gdx.app.exit();
        }

        game.batch.end();
    }
    /*Called when this screen should release all resources*/
    @Override
    public void dispose() {
        optionsButton.dispose();
        title.dispose();
        background.dispose();
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
