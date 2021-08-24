package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HowToPlayScreen implements Screen {
    final MainGame game;
    Texture background;
    MyButton backButton;
    public HowToPlayScreen(final MainGame game){
        this.game=game;
        backButton = new MyButton("HowToPlayScreen/Back_Active.png", "HowToPlayScreen/Back_Inactive.png",
                game.width - 150f, 0f,150f, 50f, game.width, game.height);
        background = new Texture("HowToPlayScreen/background.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(background,0f,0f,game.width,game.height);
        backButton.draw(game.batch);
        if (Gdx.input.justTouched() && backButton.isActive()){
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }
        game.batch.end();
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
    public void hide() { }

    @Override
    public void show() { }
}
