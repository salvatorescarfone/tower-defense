package com.mygdx.game.GameObjects;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MainGame.MainGame;
import com.mygdx.game.Screens.MainMenuScreen;

public class NewGameBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked(MainGame game) {
        game.setScore(0);
        game.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Music/menu.mp3")));
        game.setScreen(new MainMenuScreen());
    }
}
