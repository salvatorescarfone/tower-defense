package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
public abstract class Enemy extends Animatable implements Actors{

    int atkPower;
    int life;
    int runSpeed = 3;
    long lastEnemyDamage;
    Music attackFx;
    Music hitFx;
    Music deathFx;
    Music secondaryHitFx;

    Enemy() {
        super("animations/archer/running.atlas", Gdx.graphics.getWidth(),7f, 68f, 60f);
        //same hit and death sound for both enemies
        this.hitFx = Gdx.audio.newMusic(Gdx.files.internal("SoundFx/damaged_enemy.mp3"));
        this.secondaryHitFx = Gdx.audio.newMusic(Gdx.files.internal("SoundFx/damaged_enemy.mp3"));
        this.deathFx = Gdx.audio.newMusic(Gdx.files.internal("SoundFx/enemy_death.mp3"));
    }


    public abstract void movement(Tower t);


    public abstract void Idle();


    public void gotHit(){
        if (this.life!=0){
            this.life--;
        }

        if(this.hitFx.isPlaying())
            this.hitFx.stop();
        this.hitFx.play();
    }
    public abstract boolean isDead();

    public void stopSounds(){
        this.attackFx.stop();
        this.deathFx.stop();
        this.hitFx.stop();
    }
    public void dispose(){
        super.dispose();
        attackFx.dispose();
        deathFx.dispose();
        hitFx.dispose();
        secondaryHitFx.dispose();
    }

}
