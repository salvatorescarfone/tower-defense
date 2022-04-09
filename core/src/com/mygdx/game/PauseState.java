package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class PauseState extends GameState{
    @Override
    public void pause() {
        //..
    }

    @Override
    public void resume() {
        game.setPaused(false);
        game.setPauseTime(TimeUtils.nanoTime() -game.getPauseTime());
        game.setCurrentState(game.getRunningState());
    }
}
