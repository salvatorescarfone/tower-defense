package com.mygdx.game.GameObjects;

import com.mygdx.game.Screens.MainMenuScreen;

public class BackButtonBehaviour implements DoBehaviour {
    @Override
    public void buttonClicked() {
        game.setScreen(new MainMenuScreen());
    }
}
