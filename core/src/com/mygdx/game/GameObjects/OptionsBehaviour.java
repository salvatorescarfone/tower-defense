package com.mygdx.game.GameObjects;

import com.mygdx.game.Screens.OptionsScreen;

public class OptionsBehaviour implements DoBehaviour{

    @Override
    public void buttonClicked() {
        game.setScreen(new OptionsScreen());
    }
}
