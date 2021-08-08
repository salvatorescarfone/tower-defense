package com.mygdx.game;

/*Main class Regarding every animatable object or character on the screen.
 *
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Animatable {

    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    float elapsedTime = 0f;
    Rectangle hitBox;

    //add method visibility
    void animate(String selectAnimationAtlas, float frameDuration){
        textureAtlas = new TextureAtlas(Gdx.files.internal(selectAnimationAtlas));//update texture atlas
        animation = new Animation<TextureRegion>(frameDuration, textureAtlas.getRegions()); //update animation
    }
}