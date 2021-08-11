package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Tower extends Drawable{

    long lastDamageTime;
    int towerLife;

    public Tower() {
        super(0f,0f,477f,628f);//real dimensions 477x628 ????
        towerLife=10000;
        img = new Texture("tower.png");
        lastDamageTime= TimeUtils.nanoTime();
    }
    public void DamageTower(int enemyAtk) {

        lastDamageTime = TimeUtils.nanoTime();
        if (towerLife >= enemyAtk) {
            towerLife -= enemyAtk;
        }
        else {
            towerLife = 0;
        }

        if (towerLife <= 0) {
            /*Close game*/
            Gdx.app.exit();
        }
    }

}
