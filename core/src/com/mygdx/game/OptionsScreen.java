package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class OptionsScreen implements Screen {

    final MainGame game;
    GlyphLayout optionsText;



    public OptionsScreen(final MainGame game){
        this.game=game;
        optionsText=new GlyphLayout(game.font,"This is the options Menu, click anywhere to close it");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,0);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.font.draw(game.batch,optionsText,Gdx.graphics.getWidth()/2 - optionsText.width/3, Gdx.graphics.getHeight() - optionsText.height/3);

        if (Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
