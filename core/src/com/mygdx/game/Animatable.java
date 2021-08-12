package com.mygdx.game;

/*Main class Regarding every animatable object or character on the screen.
 *
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;


public class Animatable extends Drawable{

    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    float elapsedTime = 0f;
    String currentAtlasUrl;

    Animatable(String initialAnimationAtlas,float x, float y, float width, float height){
        super(x,y,width,height);
        this.currentAtlasUrl = initialAnimationAtlas;
    }

    //add method visibility
    void animate(SpriteBatch batch, float fps){
        elapsedTime += Gdx.graphics.getDeltaTime();
        textureAtlas = new TextureAtlas(Gdx.files.internal(this.currentAtlasUrl));
        animation = new Animation<TextureRegion>(1f/fps, textureAtlas.getRegions());
        batch.draw(animation.getKeyFrame(elapsedTime, true), hitBox.x, hitBox.y);
    }



}