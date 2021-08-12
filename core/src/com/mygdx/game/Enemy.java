package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Animatable{

    float elapsedTime;
    int atkPower;
    //boolean airBorn;
    int runSpeed = 3;

    public Enemy(int atkPower, boolean airBorn) {

        super("characters/archer/archer_running.atlas",1280 - (64f/2f), 0, 128f, 128f);
        this.atkPower = atkPower;

        //this.airBorn = airBorn;

        /*
        if (airBorn) {
            super.hitBox.y = 500;
            enemyImg = new Texture("flying_enemy.png");
        } else {
            super.hitBox.y = 0;
            enemyImg = new Texture("walking_enemy.png");
        }
          */

    }

    public void Attack(Tower t){
        if (this.hitBox.overlaps(t.hitBox)) {
            this.hitBox.x = t.hitBox.x+t.hitBox.width;

            if (TimeUtils.nanoTime() - t.lastDamageTime >= (1000000000 / 2)) {
                t.DamageTower(this.atkPower);
            }
        }
    }

    public void EnemyMovement(Tower t) {
        if(this.hitBox.x < t.hitBox.x+t.hitBox.width+200){
            this.runSpeed = 0;
            this.LowAttack();
            this.LoopLowAttack();
        }

        else
            hitBox.x -= runSpeed;
    }

    public void Idle(){
        this.currentAtlasUrl = "characters/archer/archer_idle.atlas";
    }

    public void Running(){
        this.currentAtlasUrl = "characters/archer/archer_running.atlas";
    }

    public void Death(){
        this.currentAtlasUrl = "characters/archer/archer_idle.atlas";
    }

    public void LowAttack(){
        this.currentAtlasUrl = "characters/archer/archer_start_low_attack.atlas";
        this.currentlyLooping=false;
    }

    public void LoopLowAttack(){
        this.currentAtlasUrl = "characters/archer/archer_loop_low_attack.atlas";
        this.currentlyLooping = true;
    }

}
