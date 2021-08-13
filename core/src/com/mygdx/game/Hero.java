package com.mygdx.game;

/*Main class Regarding the Character that will be used by the user to fight enemies.
 *
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Hero extends Animatable{

    Rectangle weaponHitBox;
    Texture weaponImg;
    int atk;

    Hero(float x, float y) {
        super("characters/hero/hero_idle.atlas", x, y, 57f, 86f);//Hitbox not drawn on character
        //weaponHitBox = new Rectangle((200f - (128f / 2f)), 164f, 64f, 64f);
        int atk=5;
        //img = new Texture("hero_idle_1.png");
        //weaponImg=new Texture("Weapon.png");
    }


    public void Idle(){
        this.currentAtlasUrl = "characters/hero/hero_idle.atlas";
    }

    public void Death(){
        this.currentAtlasUrl = "characters/hero/hero_death.atlas";
    }

}