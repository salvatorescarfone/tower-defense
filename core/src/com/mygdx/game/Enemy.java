package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Animatable{

    float elapsedTime;
    int atkPower;
    //boolean airBorn;

    public Enemy(int atkPower, boolean airBorn) {

        super(1280 - (64f/2f), 0, 128f, 128f);
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

    public void EnemyMovement() {

        hitBox.x -= 3;
    }
}
