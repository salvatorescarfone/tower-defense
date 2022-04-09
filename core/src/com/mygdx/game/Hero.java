package com.mygdx.game;

/*Main class Regarding the Character that will be used by the user to fight enemies.
 *
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Hero extends Animatable{
    private Preferences heroSkin;
    Hero(String deathAtlas, float x, float y, float width, float height) {
        super("animations/hero/hero_idle.atlas", x, y, width, height);
        heroSkin = Gdx.app.getPreferences("heroSkin");
        if (!heroSkin.contains("idle")){
            heroSkin.putString("idle","animations/hero/hero_idle.atlas" );
            heroSkin.putString("death",deathAtlas);
            heroSkin.putFloat("x",x);
            heroSkin.putFloat("y",y);
            heroSkin.putFloat("width",width);
            heroSkin.putFloat("height",height);
            heroSkin.flush();
        }
        this.currentAtlasUrl= heroSkin.getString("idle");
    }
    public void Idle(){
        this.currentAtlasUrl = heroSkin.getString("idle");
        this.textureAtlas = new TextureAtlas(Gdx.files.internal(currentAtlasUrl));
    }
    public void Death(){
        this.currentAtlasUrl = heroSkin.getString("death");
    }

    public void changeSkin(String idleAtlas, String deathAtlas, float x, float y, float width, float height){
        heroSkin.putString("idle",idleAtlas );
        heroSkin.putString("death",deathAtlas);
        heroSkin.putFloat("x",x);
        heroSkin.putFloat("y",y);
        heroSkin.putFloat("width",width);
        heroSkin.putFloat("height",height);
        heroSkin.flush();
        this.hitBox.x=x;
        this.hitBox.y=y;
        this.hitBox.width=width;
        this.hitBox.height=height;
    }
    public void act(SpriteBatch batch){
        this.animate(batch, 11f);
    }
}