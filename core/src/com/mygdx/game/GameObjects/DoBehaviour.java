package com.mygdx.game.GameObjects;

import com.mygdx.game.MainGame.MainGame;

public interface DoBehaviour {
    MainGame game = (MainGame) MainGame.getInstance();
    public void buttonClicked();
}
