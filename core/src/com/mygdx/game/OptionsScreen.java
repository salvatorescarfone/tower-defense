package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class OptionsScreen implements Screen {

    final MainGame game;
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
    private final float ALL_BUTTONS_HEIGHT=234f;

    public OptionsScreen(final MainGame game){
        this.game=game;
        game.font.setColor(Color.WHITE);
        quitGameButton= new MyButton("OptionsScreen/QuitGame_Active.png","OptionsScreen/QuitGame_Inactive.png",
                game.width/2f - BUTTONS_WIDTH/2f,game.height/2f - BUTTONS_HEIGHT/2f,BUTTONS_WIDTH,BUTTONS_HEIGHT,
                game.width,game.height);
        quitMenuButton= new MyButton("OptionsScreen/QuitMenu_Active.png", "OptionsScreen/QuitMenu_Inactive.png",
                game.width / 2f - BUTTONS_WIDTH / 2f, game.height/2f - BUTTONS_HEIGHT * 1.5f - 10f, BUTTONS_WIDTH,
                BUTTONS_HEIGHT,game.width,game.height);
        howToPlayButton= new MyButton("OptionsScreen/HowToPlay_Active.png","OptionsScreen/HowToPlay_Inactive.png",
                game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 3.5f - 30f, BUTTONS_WIDTH,
                BUTTONS_HEIGHT, game.width, game.height);
        optionsText=new GlyphLayout(game.font,"Game Created by Salvatore Scarfone and Massimo Gianotti, version 1.0");
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
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(background,0f,0f, game.width, game.height);
        game.font.draw(game.batch,optionsText,game.width/2f - optionsText.width/2f, game.height - optionsText.height/3f);
        quitGameButton.draw(game.batch);
        if (quitGameButton.isActive() && Gdx.input.justTouched()){
            Gdx.app.exit();
            this.dispose();
        }

        quitMenuButton.draw(game.batch);
        if (quitMenuButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }

        if(game.music.isPlaying()){
            game.batch.draw(musicOnInactive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            if (Gdx.input.getX() < game.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > game.width/2f - BUTTONS_WIDTH/2f
                    && game.height - Gdx.input.getY() < game.height/2f - BUTTONS_HEIGHT*1.5f -20f  && game.height - Gdx.input.getY() > game.height/2f - BUTTONS_HEIGHT*2.5f -20f) {
                game.batch.draw(musicOnActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);

                if(Gdx.input.justTouched()){
                    //music:off (active)
                    game.musicOn = false;
                    game.music.stop();
                    game.batch.draw(musicOffActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);

                }
            }
        }
        else if (Gdx.input.getX() < game.width/2f + BUTTONS_WIDTH*.5f && Gdx.input.getX() > game.width/2f - BUTTONS_WIDTH/2f
                && game.height - Gdx.input.getY() < game.height/2f - BUTTONS_HEIGHT*1.5f -20f  && game.height - Gdx.input.getY() > game.height/2f - BUTTONS_HEIGHT*2.5f -20f){
            game.batch.draw(musicOffActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            if(Gdx.input.justTouched()){
                //music:on (active)
                game.musicOn = true;
                game.music.play();
                game.music.setLooping(true);
                game.batch.draw(musicOnActive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);
            }
        }
        else
            game.batch.draw(musicOffInactive, game.width / 2f - BUTTONS_WIDTH / 2f, game.height / 2f - BUTTONS_HEIGHT * 2.5f - 20f, BUTTONS_WIDTH, BUTTONS_HEIGHT);


        howToPlayButton.draw(game.batch);
        if (howToPlayButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            game.setScreen(new HowToPlayScreen(game));
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
