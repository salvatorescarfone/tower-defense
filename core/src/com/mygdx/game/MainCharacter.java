package com.mygdx.game;

/*Main class Regarding the Character that will be used by the user to fight enemies.
 *
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class MainCharacter {
    Texture mainCharacterImg;
    Rectangle hitBox;
    Rectangle weaponHitBox;
    Texture weaponImg;
    int atk;

    public MainCharacter() {
        hitBox = new Rectangle((200f - 128f), 100f, 128f, 128f);
        weaponHitBox = new Rectangle((200f - (128f / 2f)), 164f, 64f, 64f);
        int atk=5;
        mainCharacterImg=new Texture("MainCharacter.png");
        weaponImg=new Texture("Weapon.png");
    }
}