package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Tower extends Drawable{

    long lastDamageTime;
    int towerLife;

    public Tower(float x, float y) {
        super(x,y,325f,628f);
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
