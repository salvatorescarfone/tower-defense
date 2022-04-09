package com.mygdx.game.GameObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;


public abstract class Animatable extends Drawable{

    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    float elapsedTime = 0f;
    String currentAtlasUrl;
    boolean currentlyLooping = true;

    Animatable(String initialAnimationAtlas,float x, float y, float width, float height){
        super(x,y,width,height);
        this.currentAtlasUrl = initialAnimationAtlas;
        this.textureAtlas = new TextureAtlas(Gdx.files.internal(this.currentAtlasUrl));
    }
    //add method visibility
    public void animate(SpriteBatch batch, float fps){
        elapsedTime += Gdx.graphics.getDeltaTime();
        this.textureAtlas.dispose();
        this.textureAtlas = new TextureAtlas(Gdx.files.internal(this.currentAtlasUrl));
        animation = new Animation<TextureRegion>(1f/fps, textureAtlas.getRegions());
        batch.draw(animation.getKeyFrame(elapsedTime, currentlyLooping), hitBox.x, hitBox.y);
    }
    public void stop(SpriteBatch batch, float delta){
        elapsedTime=delta;
        animation = new Animation<TextureRegion>(1f, textureAtlas.getRegions());
        batch.draw(animation.getKeyFrame(elapsedTime, currentlyLooping), hitBox.x, hitBox.y);

    }
    public void dispose(){
        this.textureAtlas.dispose();
    }
}