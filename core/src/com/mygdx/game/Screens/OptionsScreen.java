package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameObjects.AbstractGameObjectFactory;
import com.mygdx.game.GameObjects.GameObjectFactory;
import com.mygdx.game.GameObjects.MyButton;
import com.mygdx.game.GameObjects.MyMusicButton;
import com.mygdx.game.MainGame.MainGame;

public class OptionsScreen implements Screen {

    private MainGame game;
    private AbstractGameObjectFactory creator;
    private MyButton quitGameButton;
    private MyButton quitMenuButton;
    private MyButton howToPlayButton;
    private MyMusicButton musicButton;
    private GlyphLayout optionsText;
    private Texture background;
    public OptionsScreen(){
        this.game =(MainGame)MainGame.getInstance();
        this.creator = new GameObjectFactory();
        quitGameButton= creator.createButton("quitgame");
        quitMenuButton= creator.createButton("quitmenu");
        howToPlayButton= creator.createButton("howtoplay");
        musicButton = (MyMusicButton) creator.createButton("music");
        optionsText=creator.createText("Game Created by Salvatore Scarfone and Massimo Gianotti, version 1.0","white");
        background = new Texture("OptionsScreen/Stars.png");
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getCamera().update();
        game.getBatch().begin();
        game.getBatch().draw(background,0f,0f, game.getWidth(), game.getHeight());
        game.getFont().draw(game.getBatch(),optionsText, game.getWidth() /2f - optionsText.width/2f, game.getHeight() - optionsText.height/3f);
        quitGameButton.act(game, this);
        quitMenuButton.act(game, this);
        musicButton.act(game);
        howToPlayButton.act(game, this);
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        background.dispose();
        quitGameButton.dispose();
        quitMenuButton.dispose();
        howToPlayButton.dispose();
        musicButton.dispose();
    }
}