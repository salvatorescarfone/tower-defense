package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame.MainGame;

public class MyMusicButton extends MyButton{
    private Texture offActive;
    private Texture offInactive;
    private boolean playing;

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public MyMusicButton(String active, String inactive, float x, float y, float width, float height, float screenWidth, float screenHeight, boolean playing) {
        super(active, inactive, x, y, width, height, screenWidth, screenHeight, new MusicButtonBehaviour());
        offActive = new Texture("OptionsScreen/MusicOFF_Active.png");
        offInactive = new Texture("OptionsScreen/MusicOFF_Inactive.png");
        this.playing = playing;
    }
    public void draw(SpriteBatch batch){
        if (playing && Gdx.input.getX() < this.x + this.width && Gdx.input.getX() > this.x
                && this.screenHeight - Gdx.input.getY() < this.y + this.height &&
                this.screenHeight - Gdx.input.getY() > this.y){
            batch.draw(this.active,this.x,this.y,this.width,this.height);
            mouseIsHovering=true;
        }
        else if (playing && !(Gdx.input.getX() < this.x + this.width && Gdx.input.getX() > this.x
                && this.screenHeight - Gdx.input.getY() < this.y + this.height && this.screenHeight - Gdx.input.getY() > this.y)){
            batch.draw(this.inactive,this.x,this.y,this.width,this.height);
            mouseIsHovering=false;
        }
        else if (!playing && Gdx.input.getX() < this.x + this.width && Gdx.input.getX() > this.x
                && this.screenHeight - Gdx.input.getY() < this.y + this.height &&
                this.screenHeight - Gdx.input.getY() > this.y){
            batch.draw(this.offActive,this.x,this.y,this.width,this.height);
            mouseIsHovering=true;

        }
        else{
            batch.draw(this.offInactive,this.x,this.y,this.width,this.height);
            mouseIsHovering=false;
        }
    }
    public void dispose(){
        offActive.dispose();
        offInactive.dispose();
        active.dispose();
        inactive.dispose();

    }
    public void act(MainGame game){
        this.draw(game.getBatch());
        if (this.isActive() && Gdx.input.justTouched()) {
            if (game.isMusicOn()) {
                this.setPlaying(false);
                getDb().buttonClicked(game);
            }
            else{
                this.setPlaying(true);
                getDb().buttonClicked(game);
            }
        }
    }
}
