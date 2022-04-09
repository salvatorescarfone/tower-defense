package com.mygdx.game;

public class MusicButtonBehaviour implements DoBehaviour{
    @Override
    public void buttonClicked() {
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
