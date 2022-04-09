package com.mygdx.game.MainGame;

import com.badlogic.gdx.utils.TimeUtils;

public class RunningState extends GameState{
    @Override
    public void pause() {
        game.setCurrentState(game.getPauseState());
        game.setPaused(true);
        game.setPauseTime(TimeUtils.nanoTime());
    }

    @Override
    public void resume() {
        //..
    }
}
