package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HowToPlayScreen implements Screen {
    private MainGame game;
    private Texture background;
    private MyButton backButton;
    private AbstractGameObjectFactory creator;
    public HowToPlayScreen(){

        this.game =(MainGame) MainGame.getInstance();
        creator = new GameObjectFactory();
        backButton = creator.createButton("back");
        background = new Texture("HowToPlayScreen/background.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getCamera().update();
        game.getBatch().begin();

        game.getBatch().draw(background,0f,0f, game.getWidth(), game.getHeight());

        backButton.act(game.getBatch());
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }

        game.getBatch().end();
    }

    @Override
    public void dispose() {
        backButton.dispose();
        background.dispose();
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
    public void show() { }
}
