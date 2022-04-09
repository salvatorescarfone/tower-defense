package com.mygdx.game.MainGame;

public abstract class GameState {
    MainGame game = (MainGame) MainGame.getInstance();

    public abstract void pause();
    public abstract void resume();
}
