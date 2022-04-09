package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class Tower extends Drawable{

    long lastDamageTime;
    int towerLife;
    Texture img;
    public Tower(float x, float y, float width, float height) {
        super(x,y,width,height);
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
    }
    public void dispose(){
        this.img.dispose();
    }

    public int getTowerLife() {
        return towerLife;
    }

    public void setTowerLife(int towerLife) {
        this.towerLife = towerLife;
    }

    public Texture getImg() {
        return img;
    }

}
