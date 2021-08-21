package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HowToPlayScreen implements Screen {
    final MainGame game;
    Texture background;
    Texture backInactive;
    Texture backActive;
    public HowToPlayScreen(final MainGame game){
        this.game=game;
        background = new Texture("HowToPlayScreen/background.png");
        backInactive = new Texture("HowToPlayScreen/Back_Inactive.png");
        backActive = new Texture("HowToPlayScreen/Back_Active.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(background,0f,0f,game.width,game.height);
        if (Gdx.input.getX() < game.width && Gdx.input.getX() > game.width - backActive.getWidth()
            && game.height - Gdx.input.getY() < backActive.getHeight() && game.height - Gdx.input.getY() > 0f) {
            game.batch.draw(backActive, game.width - backActive.getWidth(), 0f);
            if (Gdx.input.justTouched()){
                this.dispose();
                game.setScreen(new OptionsScreen(game));
            }
        }
        else {
            game.batch.draw(backInactive, game.width - backActive.getWidth(), 0f);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }
        game.batch.end();
    }

    @Override
    public void dispose() {
        backActive.dispose();
        backInactive.dispose();
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
