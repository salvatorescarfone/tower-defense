package com.mygdx.game.GameObjects;

import com.mygdx.game.MainGame.MainGame;
import com.mygdx.game.Screens.OptionsScreen;

public class OptionsBehaviour implements DoBehaviour{

    @Override
    public void buttonClicked(MainGame game) {
        game.setScreen(new OptionsScreen());
    }
}
