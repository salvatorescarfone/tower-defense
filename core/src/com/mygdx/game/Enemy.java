package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    Texture enemyImg;
    Rectangle hitBox;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> animation;
    float elapsedTime;
    int atkPower;
    boolean airBorn;

    public Enemy(int atkPower, boolean airBorn) {
        hitBox = new Rectangle();
        hitBox.width = 128;
        hitBox.height = 128;
        hitBox.x = 1280 - (64f / 2f);
        this.atkPower = atkPower;
        this.airBorn = airBorn;
        if (airBorn) {
            hitBox.y = 500;
            enemyImg = new Texture("flying_enemy.png");
        } else {
            hitBox.y = 0;
            enemyImg = new Texture("walking_enemy.png");

        }
        TextureRegion[][] tmpFrames = TextureRegion.split(enemyImg, 128, 128);
        animationFrames = new TextureRegion[4];
        int index = 0;
        //Load Frames
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                animationFrames[index] = tmpFrames[j][i];
                index++;
            }
        }
        animation = new Animation<>(0.25f, animationFrames);
    }

    public void EnemyMovement() {

        hitBox.x -= 3;
    }
}
