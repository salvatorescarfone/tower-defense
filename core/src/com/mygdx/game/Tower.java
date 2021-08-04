package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Tower {
    Rectangle hitBox;
    Texture towerImage;
    long lastDamageTime;
    int towerLife;

    public Tower() {
        hitBox = new Rectangle();
        hitBox.x=0;
        hitBox.y=0;
        hitBox.height= 660;
        hitBox.width=200;
        towerLife=10000;
        towerImage = new Texture("temp.png");
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
