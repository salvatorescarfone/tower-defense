package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class OptionsScreen implements Screen {

    final MainGame GAME;
    MyButton quitGameButton;
    MyButton quitMenuButton;
    MyButton howToPlayButton;
    GlyphLayout optionsText;
    Texture musicOnActive;
    Texture musicOnInactive;
    Texture musicOffActive;
    Texture musicOffInactive;
    Texture background;
    private final float BUTTONS_WIDTH=150f;
    private final float BUTTONS_HEIGHT=50f;
    public OptionsScreen(final MainGame game){
        this.GAME =game;
        GAME.font.setColor(Color.WHITE);
        quitGameButton= new MyButton("OptionsScreen/QuitGame_Active.png","OptionsScreen/QuitGame_Inactive.png",
                GAME.width/2f - BUTTONS_WIDTH/2f,GAME.height/2f - BUTTONS_HEIGHT/2f,BUTTONS_WIDTH,BUTTONS_HEIGHT,
                GAME.width,GAME.height);
        quitMenuButton= new MyButton("OptionsScreen/QuitMenu_Active.png", "OptionsScreen/QuitMenu_Inactive.png",
                GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height/2f - BUTTONS_HEIGHT * 1.5f - 10f, BUTTONS_WIDTH,
                BUTTONS_HEIGHT,GAME.width,GAME.height);
        howToPlayButton= new MyButton("OptionsScreen/HowToPlay_Active.png","OptionsScreen/HowToPlay_Inactive.png",
                GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 3.5f - 30f, BUTTONS_WIDTH,
                BUTTONS_HEIGHT, GAME.width, GAME.height);
        optionsText=new GlyphLayout(GAME.font,"Game Created by Salvatore Scarfone and Massimo Gianotti, version 1.0");
        background = new Texture("OptionsScreen/Stars.png");
        musicOffActive = new Texture ("OptionsScreen/MusicOFF_Active.png");
        musicOffInactive = new Texture ("OptionsScreen/MusicOFF_Inactive.png");
        musicOnActive = new Texture ("OptionsScreen/MusicON_Active.png");
        musicOnInactive = new Texture ("OptionsScreen/MusicON_Inactive.png");
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);
        GAME.batch.setProjectionMatrix(GAME.camera.combined);
        GAME.camera.update();
        GAME.batch.begin();
        GAME.batch.draw(background,0f,0f, GAME.width, GAME.height);
        GAME.font.draw(GAME.batch,optionsText, GAME.width/2f - optionsText.width/2f, GAME.height - optionsText.height/3f);
        quitGameButton.draw(GAME.batch);
        if (quitGameButton.isActive() && Gdx.input.justTouched()){
            Gdx.app.exit();
            this.dispose();
        }

        quitMenuButton.draw(GAME.batch);
        if (quitMenuButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            GAME.setScreen(new MainMenuScreen(GAME));
        }

        if(GAME.music.isPlaying()){
            GAME.batch.draw(musicOnInactive, GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            if (Gdx.input.getX() < GAME.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > GAME.width/2f - BUTTONS_WIDTH/2f
                    && GAME.height - Gdx.input.getY() < GAME.height/2f - BUTTONS_HEIGHT*1.5f -20f  && GAME.height - Gdx.input.getY() > GAME.height/2f - BUTTONS_HEIGHT*2.5f -20f) {
                GAME.batch.draw(musicOnActive, GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);

                if(Gdx.input.justTouched()){
                    //music:off (active)
                    GAME.musicOn = false;
                    GAME.music.stop();
                    GAME.batch.draw(musicOffActive, GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);

                }
            }
        }
        else if (Gdx.input.getX() < GAME.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > GAME.width/2f - BUTTONS_WIDTH/2f
                && GAME.height - Gdx.input.getY() < GAME.height/2f - BUTTONS_HEIGHT*1.5f -20f  && GAME.height - Gdx.input.getY() > GAME.height/2f - BUTTONS_HEIGHT*2.5f -20f){
            GAME.batch.draw(musicOffActive, GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            if(Gdx.input.justTouched()){
                //music:on (active)
                GAME.musicOn = true;
                GAME.music.play();
                GAME.music.setLooping(true);
                GAME.batch.draw(musicOnActive, GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            }
        }
        else
            GAME.batch.draw(musicOffInactive, GAME.width / 2f - BUTTONS_WIDTH / 2f, GAME.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);


        howToPlayButton.draw(GAME.batch);
        if (howToPlayButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            GAME.setScreen(new HowToPlayScreen(GAME));
        }
        GAME.batch.end();
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
        background.dispose();
        quitGameButton.dispose();
        quitMenuButton.dispose();
        musicOnInactive.dispose();
        musicOffActive.dispose();
        musicOnActive.dispose();
        musicOffInactive.dispose();
        howToPlayButton.dispose();
    }
}
