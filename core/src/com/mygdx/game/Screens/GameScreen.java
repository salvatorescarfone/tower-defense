package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.GameObjects.*;
import com.mygdx.game.MainGame.MainGame;
import com.mygdx.game.MainGame.PauseState;

import java.util.Iterator;

/* This is the Screen where the core of the game resides. In here all "actors" such as hero, enemies and the tower
 * are drawn on the scene and act in a particular way. The hero is piloted by the user while the enemies are randomly
 * spawned on screen.
 */


public class GameScreen implements Screen {
    private MainGame game;
    Array<Enemy> enemies;
    private boolean hasEnemy;
    Texture background;
    Texture pauseText;
    Tower tower;
    Hero hero;
    FireBall weapon;
    private long timeOfDeath;
    private AbstractGameObjectFactory creator;
    public GameScreen(){
        this.game= (MainGame)MainGame.getInstance();
        creator = new GameObjectFactory();
        hasEnemy=false;
        pauseText = new Texture("GameScreen/Pause.png");
        tower = creator.createTower();
        background = new Texture("backgrounds/background.png");
        hero = creator.createHero();
        weapon = new FireBall(280 + hero.hitBox.getWidth()*1.5f,385f + hero.hitBox.getHeight()/2f);
        enemies = new Array<>();
        timeOfDeath=0;
    }
    public void spawnEnemy(){
        Enemy enemy = creator.createEnemy();
        enemies.add(enemy);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0);
        if (game.getCurrentState() instanceof PauseState){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                game.getCurrentState().resume();
            }
            for (Enemy enemy: enemies){
                enemy.stop(game.getBatch(),delta);
                enemy.stopSounds();
            }
            hero.stop(game.getBatch(),delta);
            weapon.stop(game.getBatch(),delta);
            //metodo di libGDX
            game.getBatch().draw(pauseText, game.getWidth() /2f -
                    pauseText.getWidth()/2f, game.getHeight() /2f - pauseText.getHeight()/2f);

            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                game.setScore(0);
                game.getMusic().stop();
                game.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Music/menu.mp3")));
                this.dispose();
                game.setScreen(new MainMenuScreen());
            }

        }
        else{
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                game.getCurrentState().pause();
                game.getMusic().pause();
            }

            if(game.isMusicOn())
                game.getMusic().play();
            if (!hasEnemy){
                spawnEnemy();
                hasEnemy=true;
            }

            for (Enemy enemy: enemies){
                enemy.act(game.getBatch(),weapon,tower,timeOfDeath);
            }
            if (TimeUtils.millis() - timeOfDeath >1000) {
                for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext(); ) {
                    Enemy e = iterator.next();
                    if (e.isDead()) {
                        iterator.remove();
                        hasEnemy = false;
                    }
                }
                timeOfDeath=0;
            }
            if (tower.getTowerLife() == 0){
                game.getMusic().stop();
                for(Enemy en : enemies){
                    en.stopSounds();
                }
                this.dispose();
                game.setScreen(new GameOverScreen());
            }

            hero.act(game.getBatch());
            weapon.draw(game.getBatch(), delta);
        }
        game.getFont().draw(game.getBatch(),creator.createText( createStr(tower.getTowerLife(), game.getScore()),"white"), 250f, 660f);
        tower.draw(game.getBatch(), tower.getImg());

        game.getBatch().end();
    }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() {
        game.getMusic().dispose();
        game.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Music/in_game.mp3")));
        if(game.isMusicOn()){
            game.getMusic().setLooping(true);
            game.getMusic().play();
        }
    }
    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
        weapon.dispose();
        for (Enemy e: enemies){
            e.dispose();
        }
        tower.dispose();
        hero.dispose();
    }
    private String createStr(int life, int score){
        StringBuilder sb = new StringBuilder();
        sb.append("Life: ");
        sb.append(life);
        sb.append(" Score: ");
        sb.append(score);
        return sb.toString();
    }
}


