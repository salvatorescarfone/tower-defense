package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MainGame.MainGame;

public class Knight extends Enemy {
    public Knight() {
        super();
        this.currentAtlasUrl = ("animations/knight/running.atlas");
        this.atkPower = 200;
        this.life = 5;
        this.attackFx = Gdx.audio.newMusic(Gdx.files.internal("SoundFx/swoosh.mp3"));

    }

    private void doDamage(Tower t) {
        if (this.hitBox.overlaps(t.hitBox)) {
            if (TimeUtils.nanoTime() - this.lastEnemyDamage >= (1000000000)) {
                t.DamageTower(this.atkPower);
                this.lastEnemyDamage = TimeUtils.nanoTime();
            }
        }
    }

    public void movement(Tower t) {
        if (this.isDead()) {
            //Do nothing...
            //this.hitBox.x=this.hitBox.x;

        } else {
            if (this.hitBox.x <= 247f && !this.isDead()) {//rogue knight
                this.runSpeed = 0;
                this.Attack();
                this.doDamage(t);
                this.attackFx.setLooping(true);
                this.attackFx.play();
            } else {
                hitBox.x -= runSpeed;
            }
        }
    }

    public void Idle() {
        this.currentAtlasUrl = "animations/knight/idle.atlas";
    }

    private void Death() {
        this.hitFx.stop();
        this.attackFx.stop();
        this.deathFx.play();
        this.currentAtlasUrl = "animations/knight/death.atlas";
        this.hitBox.height = 40f;
        this.currentlyLooping = false;
    }

    private void Attack() {
        this.currentAtlasUrl = "animations/knight/attack.atlas";
        this.currentlyLooping = true;
        this.hitBox.width = 100f;
    }

    @Override
    public boolean isDead() {
        boolean isDead = false;
        if (this.life == 0) {
            isDead = true;
            this.Death();
        }
        return isDead;
    }

    @Override
    public long act(SpriteBatch batch, FireBall weapon, Tower t, long time) {
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

    @Override
    public void act(SpriteBatch batch, float fps, Rectangle rect) {
        this.animate(batch, fps);
        if (!this.hitBox.overlaps(rect)){
            this.hitBox.x -= this.runSpeed;
        }
        else{
            this.Idle();
        }
    }
}
