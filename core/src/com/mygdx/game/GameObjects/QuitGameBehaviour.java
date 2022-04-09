package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;

public class QuitGameBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked() {
        Gdx.app.exit();
    }
}
