package com.mygdx.game.GameObjects;

import com.mygdx.game.MainGame.MainGame;

public class MusicButtonBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked(MainGame game) {
        if (game.isMusicOn()){
            game.setMusicOn(false);
            game.getMusic().stop();
        }
        else{
            game.setMusicOn(true);
            game.getMusic().play();
            game.getMusic().setLooping(true);
        }
    }
}
