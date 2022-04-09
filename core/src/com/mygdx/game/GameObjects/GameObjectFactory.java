package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.MainGame.MainGame;

public class GameObjectFactory extends AbstractGameObjectFactory{
    @Override
    public Enemy createEnemy() {
        int i = MathUtils.random(0,100);
        if (i >=0 && i<=50){
            return new Archer();
        }
        else if (i>=51 && i<=100){
            return new Knight();
        }
        return null;
    }

    @Override
    public Hero createHero() {
        return new Hero("animations/hero/hero_death.atlas",280f,402f,57f, 86f);
    }

    @Override
    public Tower createTower() {
        return new Tower(50f,18f,325f,628f);
    }

    @Override
    public GlyphLayout createText(String text, String colour) {
        MainGame game = (MainGame) MainGame.getInstance();
        GlyphLayout txt= new GlyphLayout();
        if ("white".equals(colour)){
            game.getFont().setColor(Color.WHITE);
        }
        else if ("black".equals(colour)){
            game.getFont().setColor(Color.BLACK);
        }
        txt.setText(game.getFont(),text);
        return txt;
    }

    @Override
    public MyButton createButton(String type) {
        if ("options".equals(type)){
            return new MyButton("Buttons/options_active.png", "Buttons/options_inactive.png",
                    Gdx.graphics.getWidth() - 50f, Gdx.graphics.getHeight() - 49f,50f,49f, Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight(), new OptionsBehaviour());
        }
        else if ("quitgame".equals(type)){
            return new MyButton("OptionsScreen/QuitGame_Active.png","OptionsScreen/QuitGame_Inactive.png",
                    Gdx.graphics.getWidth() /2f - 150f/2f, Gdx.graphics.getHeight() /2f - 50f/2f,150f,50f,
                    Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),new QuitGameBehaviour());
        }
        else if ("quitmenu".equals(type)){
             return new MyButton("OptionsScreen/QuitMenu_Active.png", "OptionsScreen/QuitMenu_Inactive.png",
                    Gdx.graphics.getWidth() / 2f - 150f / 2f, Gdx.graphics.getHeight() /2f - 50f * 1.5f - 10f, 150f,
                    50f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new QuitMenuBehaviour());
        }
        else if ("howtoplay".equals(type)){
            return new MyButton("OptionsScreen/HowToPlay_Active.png","OptionsScreen/HowToPlay_Inactive.png",
                    Gdx.graphics.getWidth() / 2f - 150f / 2f, Gdx.graphics.getHeight() / 2f - 50f * 3.5f - 30f, 150f,
                    50f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new HowToPlayBehaviour());
        }
        else if ("music".equals(type)){
            return new MyMusicButton("OptionsScreen/MusicON_Active.png","OptionsScreen/MusicON_Inactive.png",
                    Gdx.graphics.getWidth() / 2f - 150f / 2f, Gdx.graphics.getHeight() / 2f - 50f * 2.5f - 20f, 150f,
                    50f,Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
        }
        else if("back".equals(type)){
            return new MyButton("HowToPlayScreen/Back_Active.png", "HowToPlayScreen/Back_Inactive.png",
                    Gdx.graphics.getWidth() - 150f, 0f,150f, 50f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new BackButtonBehaviour());
        }
        else if ("newgame".equals(type)){
            return new MyButton("GameOverScreen/NewGame_Active.png","GameOverScreen/NewGame_Inactive.png",
                    Gdx.graphics.getWidth() /2f - 150f/2f, Gdx.graphics.getHeight() /2f - 50f/2f + 70f, 150f,50f,
                    Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new NewGameBehaviour());
        }
        return null;
    }
}
