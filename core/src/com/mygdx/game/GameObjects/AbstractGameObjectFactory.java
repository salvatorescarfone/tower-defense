package com.mygdx.game.GameObjects;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public abstract class AbstractGameObjectFactory {
    public abstract Enemy createEnemy();
    public abstract Hero createHero();
    public abstract Tower createTower();
    public abstract GlyphLayout createText(String text, String colour);
    public abstract MyButton createButton(String type);
}
