package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemy extends Animatable{

    int atkPower;
    int life;
    int runSpeed = 3;
    long lastEnemyDamage;
    int select;
    public Enemy(int i, boolean isMainMenu) {
        super("animations/archer/running.atlas", Gdx.graphics.getWidth(),7f, 68f, 60f);
        this.select=i;
        if (isMainMenu){
            this.hitBox.y=60f;
        }
        if (select ==0){
            this.atkPower=500;
            this.life=3;
        }
        else{
            this.currentAtlasUrl=("animations/knight/running.atlas");
            this.atkPower = 200;
            this.life=5;

        }
    }

    private void doDamage(Tower t){
        if (this.select == 0) {
            if (this.hitBox.x <= t.hitBox.x + t.hitBox.width + 200f) {
                if (TimeUtils.nanoTime() - this.lastEnemyDamage >= (1000000000 / 2)) {
                    t.DamageTower(this.atkPower);
                    this.lastEnemyDamage = TimeUtils.nanoTime();
                }
            }
        }
        else{
            if (this.hitBox.overlaps(t.hitBox)){
                if (TimeUtils.nanoTime() - this.lastEnemyDamage >= (1000000000)) {
                    t.DamageTower(this.atkPower);
                    this.lastEnemyDamage = TimeUtils.nanoTime();
                }
            }
        }
    }

    public void EnemyMovement(Tower t) {
        if (this.isDead()){
            this.hitBox.x=this.hitBox.x;
        }
        else {
            if (this.select == 0) {
                if (this.hitBox.x <= t.hitBox.x + t.hitBox.width + 200f && !this.isDead()) {
                    this.runSpeed = 0;
                    this.Attack();
                    this.LoopAttack();
                    this.doDamage(t);
                } else
                    hitBox.x -= runSpeed;
            } else {
                if (this.hitBox.x <= 247f && !this.isDead()) {
                    this.runSpeed = 0;
                    this.Attack();
                    this.doDamage(t);
                } else {
                    hitBox.x -= runSpeed;
                }
            }
        }
    }

    public void Idle(){
        if (this.select==0) {
            this.currentAtlasUrl = "animations/archer/idle.atlas";
        }
        else{
            this.currentAtlasUrl= "animations/knight/idle.atlas";
        }
    }
    private void Death(){
        if (this.select==0) {
            this.currentAtlasUrl = "animations/archer/death.atlas";
            this.hitBox.height=30f;
            this.hitBox.width=100f;
        }
        else{
            this.currentAtlasUrl = "animations/knight/death.atlas";
            this.hitBox.height=40f;
        }
        this.currentlyLooping=false;

    }

    private void Attack(){
        if (this.select==0) {
            this.currentAtlasUrl = "animations/archer/lowattack.atlas";
            this.currentlyLooping=false;
        }
        else{
            this.currentAtlasUrl = "animations/knight/attack.atlas";
            this.currentlyLooping=true;
            this.hitBox.width=100f;
        }
    }

    private void LoopAttack(){
        this.currentAtlasUrl = "animations/archer/looplowattack.atlas";
        this.currentlyLooping = true;
    }
    public void gotHit(){
        if (this.life!=0){
            this.life--;
        }
        //Sound reproduction..
    }
    public boolean isDead(){
        boolean isDead=false;
        if (this.life==0){
            isDead=true;
            this.Death();
        }
        return isDead;
    }

}
