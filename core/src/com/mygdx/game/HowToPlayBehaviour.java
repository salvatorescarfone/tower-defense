package com.mygdx.game;

public class HowToPlayBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked() {
        game.setScreen(new HowToPlayScreen());
    }
}
