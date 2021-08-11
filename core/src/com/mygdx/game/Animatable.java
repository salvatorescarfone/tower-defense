package com.mygdx.game;

/*Main class Regarding every animatable object or character on the screen.
 *
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Animatable extends Drawable{

    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    float elapsedTime = 0f;

    Animatable(float x, float y, float width, float height){
        super(x,y,width,height);
    }

    //add method visibility
    void animate(String selectAnimationAtlas, float frameDuration){
        textureAtlas = new TextureAtlas(Gdx.files.internal(selectAnimationAtlas));
        animation = new Animation<TextureRegion>(frameDuration, textureAtlas.getRegions());
    }

}