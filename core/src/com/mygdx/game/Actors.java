package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Actors {
    public long act(SpriteBatch batch, FireBall weapon, Tower t, long time);
    public void act(SpriteBatch batch, float fps, Rectangle rect);
}
