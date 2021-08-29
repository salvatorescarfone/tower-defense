package com.mygdx.game;

/*Main class Regarding every drawable object or character on the screen.
 *
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Drawable {

    Rectangle hitBox;
    Drawable(float x, float y, float width, float height){
        hitBox = new Rectangle(x,y,width,height);
    }

    public void draw(SpriteBatch batch, Texture img){
        batch.draw(img, this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height);
    }

}