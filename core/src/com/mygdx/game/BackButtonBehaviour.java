package com.mygdx.game;

public class BackButtonBehaviour implements DoBehaviour {
    @Override
    public void buttonClicked() {
        game.setScreen(new MainMenuScreen());
    }
}
