package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MainGame.MainGame;

public class QuitGameBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked(MainGame game) {
        Gdx.app.exit();
    }
}
