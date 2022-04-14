package com.mygdx.game.GameObjects;

import com.mygdx.game.MainGame.MainGame;
import com.mygdx.game.Screens.HowToPlayScreen;

public class HowToPlayBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked(MainGame game) {
        game.setScreen(new HowToPlayScreen());
    }
}