package com.mygdx.game;

/*Main class Regarding the Character that will be used by the user to fight enemies.
 *
 */

public class Hero extends Animatable{
    float width;
    float height;
    Hero(float x, float y) {
        super("animations/hero/hero_idle.atlas", x, y, 57f, 86f);//Hitbox not drawn on character
        width=57f;
        height=86f;

    }
    public void Idle(){
        this.currentAtlasUrl = "animations/hero/hero_idle.atlas";
    }
    public void Death(){
        this.currentAtlasUrl = "animations/hero/hero_death.atlas";
    }
}