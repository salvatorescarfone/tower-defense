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
    TextureRegion reg;
    Sprite sprite;
    float stateTime;
    boolean isIdling;
    Vector2 mouse;
    Vector2 position;
    Vector2 velocity;
    Vector2 idlePos;
    final float speedMax = 100f;

    public Weapon(float x, float y){
        isIdling=true;
        reg=new TextureRegion();
        idleAnimation = createAnimation(new Texture("animations/fireball/idle.png"),IDLE_COLS,IDLE_ROWS,0.025f);
        movingAnimation = createAnimation(new Texture("animations/fireball/moving.png"),MOVING_COLS,MOVING_ROWS,0.05f);
        stateTime= 0f;
        reg = idleAnimation.getKeyFrame(0);
        sprite = new Sprite(reg);
        sprite.setPosition(x,y);
        idlePos=new Vector2(x,y);
        mouse=new Vector2();
        position= new Vector2(x,y);
        velocity = new Vector2();
    }

    /*Create animation with TextureRegion[][] method*/
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
        update(delta);
        if (isIdling){
            sprite.setRegion(idleAnimation.getKeyFrame(stateTime,true));
        }
        else {
            sprite.setRegion(movingAnimation.getKeyFrame(stateTime, true));
            sprite.flip(true,false);
        }
        //sprite.setRotation((spriteCenter.angleDeg(mouse)));
        sprite.draw(batch);
    }

    public void update(float delta){
        //If mouse is pressed while sprite is Idling: start attack
        if (Gdx.input.justTouched() && isIdling){
            mouse.x = Gdx.input.getX();
            mouse.y =Gdx.graphics.getHeight() - Gdx.input.getY();
            isIdling=false;
            //Reset size for move animation
            sprite.setSize(68f,9f);

        }
        if (!isIdling){
            //moveFireball
            shootTowards(mouse.x - sprite.getWidth(), mouse.y - sprite.getHeight());
            if (position.x != mouse.x && position.y != mouse.y) {
                position.add(velocity.x*delta, velocity.y*delta);
                sprite.setPosition(position.x, position.y);
            }
            else{
                sprite.setBounds(idlePos.x,idlePos.y,10f,26f);
                isIdling=true;
            }
        }

    }

    private void shootTowards(float targetX, float targetY){
        //Get normal direction vertex to the wanted position and sets speed
        velocity.set(targetX - position.x, targetY - position.y).nor().scl(Math.min(position.dst(targetX,targetY),speedMax));
    }

}


