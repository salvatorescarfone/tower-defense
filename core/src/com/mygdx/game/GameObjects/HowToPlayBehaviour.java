package com.mygdx.game.GameObjects;

import com.mygdx.game.Screens.HowToPlayScreen;

public class HowToPlayBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked() {
        game.setScreen(new HowToPlayScreen());
    }
}
