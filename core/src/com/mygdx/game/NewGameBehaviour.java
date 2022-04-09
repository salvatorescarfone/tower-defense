package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class NewGameBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked() {
        game.setScore(0);
        game.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Music/menu.mp3")));
        game.setScreen(new MainMenuScreen());
    }
}
