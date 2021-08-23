package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
    final int IDLE_COLS = 1;
    final int IDLE_ROWS = 60;
    final int MOVING_ROWS = 2;
    final int MOVING_COLS = 30;
    Animation<TextureRegion> idleAnimation;
    Animation<TextureRegion> movingAnimation;
    final float SPEED = 3f;
    TextureRegion reg;
    Sprite sprite;
    float stateTime;
    boolean isMoving;
    boolean isIdling;
    Vector2 mouse;
    Vector2 spriteCenter;

    public Weapon(float x, float y){
        isIdling=true;
        isMoving=false;
        reg = new TextureRegion();
        idleAnimation = createAnimation(new Texture("animations/fireball/idle.png"),IDLE_COLS,IDLE_ROWS,0.025f);
        movingAnimation = createAnimation(new Texture("animations/fireball/moving.png"),MOVING_COLS,MOVING_ROWS,0.05f);
        stateTime= 0f;
        reg= idleAnimation.getKeyFrame(0);
        sprite = new Sprite(reg);
        sprite.setX(x);
        sprite.setY(y);
        mouse=new Vector2();
        spriteCenter= new Vector2(sprite.getX() + sprite.getWidth()/2f,sprite.getY() + sprite.getHeight()/2f);
    }


    private Animation<TextureRegion> createAnimation(Texture sheet, int cols, int rows, float frameDuration){
        TextureRegion[][] tmp = TextureRegion.split(sheet,sheet.getWidth() / cols,
                sheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }

        return new Animation<>(frameDuration, frames);
    }


    /*Gets new frame, sets frame, draws sprite on screen*/
    public void draw(SpriteBatch batch, float delta){
        stateTime += delta;
        if (isIdling){
            reg= idleAnimation.getKeyFrame(stateTime,true);
        }
        else {
            reg = movingAnimation.getKeyFrame(stateTime, true);
        }
        sprite.setRegion(reg);
        mouse.x = Gdx.input.getX();
        mouse.y = Gdx.input.getY();
        sprite.setRotation((spriteCenter.angleDeg(mouse)));
        sprite.draw(batch);
    }

    public void update(float delta){

    }


}


