package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HowToPlayScreen implements Screen {
    final MainGame GAME;
    Texture background;
    MyButton backButton;
    public HowToPlayScreen(final MainGame game){
        this.GAME =game;
        backButton = new MyButton("HowToPlayScreen/Back_Active.png", "HowToPlayScreen/Back_Inactive.png",
                GAME.width - 150f, 0f,150f, 50f, GAME.width, GAME.height);
        background = new Texture("HowToPlayScreen/background.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        GAME.batch.setProjectionMatrix(GAME.camera.combined);
        GAME.camera.update();
        GAME.batch.begin();
        GAME.batch.draw(background,0f,0f, GAME.width, GAME.height);
        backButton.draw(GAME.batch);
        if (Gdx.input.justTouched() && backButton.isActive()){
            this.dispose();
            GAME.setScreen(new MainMenuScreen(GAME));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }
        GAME.batch.end();
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
