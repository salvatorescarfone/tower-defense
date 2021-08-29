package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyButton {
    Texture active;
    Texture inactive;
    float width;
    float height;
    float x;
    float y;
    float screenWidth;
    float screenHeight;
    boolean mouseIsHovering;

    public MyButton(String active, String inactive, float x, float y, float width, float height, float screenWidth, float screenHeight) {
        this.active = new Texture(active);
        this.inactive = new Texture(inactive);
        this.width = width;
        this.height = height;
        this.x=x;
        this.y=y;
        this.screenWidth=screenWidth;
        this.screenHeight=screenHeight;
    }
    //Handles button drawing and listens for mouse hovering
    public void draw(SpriteBatch batch){
        if (Gdx.input.getX() < this.x + this.width && Gdx.input.getX() > this.x
            && this.screenHeight - Gdx.input.getY() < this.y + this.height && this.screenHeight - Gdx.input.getY() > this.y){
            batch.draw(this.active,this.x,this.y,this.width,this.height);
            mouseIsHovering=true;
        }
        else{
            batch.draw(this.inactive,this.x,this.y,this.width,this.height);
            mouseIsHovering=false;
        }
    }

    public boolean isActive(){
        return mouseIsHovering;
    }

    public void dispose(){
        active.dispose();
        inactive.dispose();
    }

}
