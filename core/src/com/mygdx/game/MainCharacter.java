package com.mygdx.game;

/*Main class Regarding the Character that will be used by the user to fight enemies.
 *
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class MainCharacter {
    Texture mainCharacterImg;
    Rectangle hitBox;
    Rectangle weaponHitBox;
    Texture weaponImg;
    int atk;
    TextureAtlas textureAtlas;
    Animation<TextureRegion> animation;
    float elapsedTime = 0f;

    public MainCharacter() {
        hitBox = new Rectangle((200f - 128f), 100f, 128f, 128f);
        weaponHitBox = new Rectangle((200f - (128f / 2f)), 164f, 64f, 64f);
        int atk=5;
        mainCharacterImg=new Texture("MainCharacter.png");
        weaponImg=new Texture("Weapon.png");
        textureAtlas = new TextureAtlas(Gdx.files.internal("hero_idle.atlas"));
        animation = new Animation<TextureRegion>(1f/10f, textureAtlas.getRegions());
    }
}