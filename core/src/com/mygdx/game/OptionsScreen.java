package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class OptionsScreen implements Screen {

    final MainGame game;
    GlyphLayout optionsText;
    Texture quitGameInactive;
    Texture quitMenuInactive;
    Texture quitGameActive;
    Texture quitMenuActive;
    Texture musicOnActive;
    Texture musicOnInactive;
    Texture musicOffActive;
    Texture musicOffInactive;
    Texture background;
    Texture howToPlayInactive;
    Texture howToPlayActive;
    private final float BUTTONS_WIDTH=150f;
    private final float BUTTONS_HEIGHT=50f;

    public OptionsScreen(final MainGame game){
        this.game=game;
        game.font.setColor(Color.WHITE);
        optionsText=new GlyphLayout(game.font,"Game Created by Salvatore Scarfone and Massimo Gianotti, version 1.0");
        background = new Texture("OptionsScreen/Stars.png");
        quitGameActive= new Texture("OptionsScreen/QuitGame_Active.png");
        quitGameInactive= new Texture("OptionsScreen/QuitGame_Inactive.png");
        quitMenuActive= new Texture("OptionsScreen/QuitMenu_Active.png");
        quitMenuInactive= new Texture("OptionsScreen/QuitMenu_Inactive.png");
        musicOffActive = new Texture ("OptionsScreen/MusicOFF_Active.png");
        musicOffInactive = new Texture ("OptionsScreen/MusicOFF_Inactive.png");
        musicOnActive = new Texture ("OptionsScreen/MusicON_Active.png");
        musicOnInactive = new Texture ("OptionsScreen/MusicON_Inactive.png");
        howToPlayInactive = new Texture("OptionsScreen/HowToPlay_Inactive.png");
        howToPlayActive = new Texture ("OptionsScreen/HowToPlay_Active.png");
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(background,0f,0f, game.width, game.height);
        game.font.draw(game.batch,optionsText,game.width/2f - optionsText.width/3f, game.height - optionsText.height/3f);
        //Get QuitGame Bounds, highlights it if mouse hovers over it and set action if mouse button is pressed
        if (Gdx.input.getX() < game.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > game.width/2f - BUTTONS_WIDTH/2f
            && game.height - Gdx.input.getY() < game.height/2f + BUTTONS_HEIGHT*.5f && game.height - Gdx.input.getY() > game.height/2f - BUTTONS_HEIGHT/2f){
            game.batch.draw(quitGameActive,game.width/2f - BUTTONS_WIDTH/2f,game.height/2f - BUTTONS_HEIGHT/2f,BUTTONS_WIDTH,BUTTONS_HEIGHT);
            if (Gdx.input.justTouched()){
                Gdx.app.exit();
                this.dispose();
            }
        }
        else{
            game.batch.draw(quitGameInactive,game.width/2f - BUTTONS_WIDTH/2f,game.height/2f - BUTTONS_HEIGHT/2f,BUTTONS_WIDTH,BUTTONS_HEIGHT);
        }
        //Get QuitMenu Bounds, highlights it if mouse hovers over it and set action if mouse button is pressed
        if (Gdx.input.getX() < game.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > game.width/2f - BUTTONS_WIDTH/2f
                && game.height - Gdx.input.getY() < game.height/2f - BUTTONS_HEIGHT*.5f -10f  && game.height - Gdx.input.getY() > game.height/2f - BUTTONS_HEIGHT*1.5f -10f ) {
            game.batch.draw(quitMenuActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height/2f - BUTTONS_HEIGHT * 1.5f - 10f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            if (Gdx.input.justTouched()) {
                this.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else{
            game.batch.draw(quitMenuInactive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 1.5f - 10f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        }
        if (Gdx.input.getX() < game.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > game.width/2f - BUTTONS_WIDTH/2f
                && game.height - Gdx.input.getY() < game.height/2f - BUTTONS_HEIGHT*1.5f -20f  && game.height - Gdx.input.getY() > game.height/2f - BUTTONS_HEIGHT*2.5f -20f  /*&& game.music.isPlaying()*/) {
            game.batch.draw(musicOnActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        }
        else {
            game.batch.draw(musicOnInactive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        }
        if (Gdx.input.getX() < game.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > game.width/2f - BUTTONS_WIDTH/2f
                && game.height - Gdx.input.getY() < game.height/2f - BUTTONS_HEIGHT*2.5f -30f  && game.height - Gdx.input.getY() > game.height/2f - BUTTONS_HEIGHT*3.5f -30f) {
            game.batch.draw(howToPlayActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 3.5f - 30f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            if (Gdx.input.justTouched()){
                this.dispose();
                game.setScreen(new HowToPlayScreen(game));
            }
        }
        else{
            game.batch.draw(howToPlayInactive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 3.5f - 30f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        }
        game.batch.end();
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
    public void dispose() {
        quitGameActive.dispose();
        quitMenuActive.dispose();
        background.dispose();
        quitGameInactive.dispose();
        quitMenuInactive.dispose();
        musicOnInactive.dispose();
        musicOffActive.dispose();
        musicOnActive.dispose();
        musicOffInactive.dispose();
        howToPlayInactive.dispose();
        howToPlayActive.dispose();
    }
}
