package com.mygdx.game.GameObjects;

import com.mygdx.game.MainGame.MainGame;
import com.mygdx.game.Screens.MainMenuScreen;

public class BackButtonBehaviour implements DoBehaviour {
    @Override
    public void buttonClicked(MainGame game) {
        game.setScreen(new MainMenuScreen());
    }
}
