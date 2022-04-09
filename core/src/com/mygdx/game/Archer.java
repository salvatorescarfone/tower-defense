package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Archer extends Enemy{

    public Archer() {
        super();
        this.atkPower=500;
        this.life=3;
        this.attackFx = Gdx.audio.newMusic(Gdx.files.internal("SoundFx/arrow.mp3"));
        this.attackFx.setVolume(1f);
    }

    private void doDamage(Tower t){
        if (this.hitBox.x <= t.hitBox.x + t.hitBox.width + 200f) {
            if (TimeUtils.nanoTime() - this.lastEnemyDamage >= (1000000000 / 2)) {
                t.DamageTower(this.atkPower);
                this.lastEnemyDamage = TimeUtils.nanoTime();
            }
        }
    }
    @Override
    public void movement(Tower t) {
        if (this.isDead()){

        }
        else {
            if (this.hitBox.x <= t.hitBox.x + t.hitBox.width + 200f && !this.isDead()) {//archer
                this.runSpeed = 0;
                this.Attack();
                this.LoopAttack();
                this.doDamage(t);
                this.attackFx.setLooping(true);
                this.attackFx.play();
            } else {
                hitBox.x -= runSpeed;
            }
        }
    }

    @Override
    public void Idle() {
        this.currentAtlasUrl = "animations/archer/idle.atlas";
    }

    @Override
    public boolean isDead() {
        boolean isDead=false;
        if (this.life==0){
            isDead=true;
            this.Death();
        }
        return isDead;
    }

    private void Death(){
        this.hitFx.stop();
        this.attackFx.stop();
        this.deathFx.play();
        this.currentAtlasUrl = "animations/archer/death.atlas";
        this.hitBox.height=30f;
        this.hitBox.width=100f;
        this.currentlyLooping=false;
    }
    private void Attack(){
        this.currentAtlasUrl = "animations/archer/lowattack.atlas";
        this.currentlyLooping=false;
    }
    private void LoopAttack(){
        this.currentAtlasUrl = "animations/archer/looplowattack.atlas";
        this.currentlyLooping = true;
    }
    public void act(SpriteBatch batch, float fps, Rectangle rect){
        this.animate(batch, fps);
        if (!this.hitBox.overlaps(rect)){
            this.hitBox.x -= this.runSpeed;
        }
        else{
            this.Idle();
        }
    }
    public long act(SpriteBatch batch,FireBall weapon,  Tower t, long time) {
        this.movement(t);
        if (!this.isDead()) {
            this.animate(batch, 11f);
        } else {
            this.animate(batch, 5f);
        }
        if (weapon.hits(this)) {
            this.gotHit();
            if (this.isDead() && time ==0){
                MainGame game = (MainGame)MainGame.getInstance();
                game.setScore(game.getScore()+5);
                return TimeUtils.millis();
            }
        }
        return time;
    }
}
