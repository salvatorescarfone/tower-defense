package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Animatable{

    int atkPower;
    int runSpeed = 3;
    long lastEnemyDamage;
    boolean airBorn;
    public Enemy(int atkPower,boolean airBorn, float x, float y) {

        super("characters/archer/archer_running.atlas",x , y, 128f, 128f);
        this.atkPower = atkPower;
        this.airBorn = airBorn;

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
        if (this.hitBox.x < t.hitBox.x + t.hitBox.width + 200f) {

            if (TimeUtils.nanoTime() - this.lastEnemyDamage >=(1000000000/2)) {
                t.DamageTower(this.atkPower);
                this.lastEnemyDamage = TimeUtils.nanoTime();
            }
        }
    }

    public void EnemyMovement(Tower t) {
        if(this.hitBox.x <= t.hitBox.x+t.hitBox.width+200f){
            this.runSpeed = 0;
            this.LowAttack();
            this.LoopLowAttack();
            this.Attack(t);
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
