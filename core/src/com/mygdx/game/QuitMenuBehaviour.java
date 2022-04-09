package com.mygdx.game;

public class QuitMenuBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked() {
        game.setScreen(game.getMainMenuScreen());
    }
}
