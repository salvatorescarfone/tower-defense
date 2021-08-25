package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Weapon {
    final int COLS = 60;
    final int ROWS = 1;
    Animation<TextureRegion> idleAnimation;
    Sprite sprite;
    float stateTime;
    boolean isIdling = true;
    Vector2 mouse;
    Vector2 position;
    Vector2 velocity;
    Vector2 idlePos;
    float x;
    float y;
    float targetX;
    float targetY;
    final float speedMax = 400f;

    public Weapon(float x, float y){
        this.x=x;
        this.y=y;
        idleAnimation = createAnimation(new Texture("animations/fireball/idle_flipped.png"));
        stateTime= 0f;
        sprite = new Sprite(idleAnimation.getKeyFrame(0));
        sprite.setPosition(x,y);
        idlePos=new Vector2(x + sprite.getWidth()/2f,y + sprite.getHeight()/2f);
        mouse=new Vector2();
        position= new Vector2(x,y);
        velocity = new Vector2();
    }

    /*Create animation with TextureRegion[][] method*/
    private Animation<TextureRegion> createAnimation(Texture sheet){
        TextureRegion[][] tmp = TextureRegion.split(sheet,sheet.getWidth() / COLS,
                sheet.getHeight() / ROWS);
        TextureRegion[] frames = new TextureRegion[COLS * ROWS];
        int index = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }

        return new Animation<>(0.025f, frames);
    }


    /*Gets new frame, sets frame, draws sprite on screen*/
    public void draw(SpriteBatch batch, float delta){
        stateTime += delta;
        update(delta);
        if (isIdling){
            sprite.setRotation(calculateAngle());
        }
        sprite.setRegion(idleAnimation.getKeyFrame(stateTime,true));
        sprite.draw(batch);
    }

    public void update(float delta){
        mouse.x =Gdx.graphics.getHeight() - Gdx.input.getX();
        mouse.y=Gdx.graphics.getHeight() - Gdx.input.getY();
        //If mouse is pressed while sprite is Idling: start attack
        if (Gdx.input.justTouched() && isIdling){
            targetX = Gdx.input.getX();
            if (targetX < idlePos.x){
                //Input Not Listened
                isIdling=true;
            }
            else {
                targetY =Gdx.graphics.getHeight() - Gdx.input.getY();
                isIdling = false;
            }
        }
        if (!isIdling){
            //moveFireball
            shootTowards(targetX , targetY);
            position.add(velocity.x*delta, velocity.y * delta);
            sprite.setPosition(position.x, position.y);
            if (sprite.getX() > Gdx.graphics.getWidth() || sprite.getY() <= 0f
                    || sprite.getY() > Gdx.graphics.getHeight()){
                setIdle();
            }
        }
    }
    private void shootTowards(float targetX, float targetY){
        velocity.set(targetX - idlePos.x, targetY - idlePos.y).nor().scl(speedMax);
    }
    public boolean hits(Enemy e){
        boolean hasHit = false;
        if (sprite.getBoundingRectangle().overlaps(e.hitBox)){
            hasHit=true;
            setIdle();
        }
        return hasHit;
    }

    public void setIdle(){
        isIdling=true;
        sprite.setPosition(x,y);
        position.set(x,y);
    }
    public void stop(SpriteBatch batch, float delta){
        stateTime=delta;
        sprite.draw(batch);
    }
    private float calculateAngle(){
        float scale= 120f/Gdx.graphics.getHeight();
        float angle=-70f + scale*mouse.y;
        if (mouse.y<=10f){
            angle=-70f;
        }
        if (mouse.y==Gdx.graphics.getHeight()){
            angle=50f;
        }
        return angle;
    }
}


