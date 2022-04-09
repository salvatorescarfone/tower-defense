package com.mygdx.game;

public class OptionsBehaviour implements DoBehaviour{

    @Override
    public void buttonClicked() {
        game.setScreen(new OptionsScreen());
    }
}
