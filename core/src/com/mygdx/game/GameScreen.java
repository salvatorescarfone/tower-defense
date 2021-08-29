package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/* This is the Screen where the core of the game resides. In here all "actors" such as hero, enemies and the tower
 * are drawn on the scene and act in a particular way. The hero is piloted by the user while the enemies are randomly
 * spawned on screen.
 */


public class GameScreen implements Screen {
    final MainGame GAME;
    Array<Enemy> enemies;
    private boolean hasEnemy;
    Texture background;
    Texture pauseText;
    BitmapFont lifeTxt;
    Tower tower;
    Hero hero;
    Weapon weapon;
    private boolean paused;
    private long pauseTime;
    private long timeOfDeath;

    /*Constructor method for the GameScreen*/
    public GameScreen(final MainGame game){
        this.GAME =game;
        //draw hit box borders
        hasEnemy=false;
        pauseTime=0;
        pauseText = new Texture("GameScreen/Pause.png");
        tower = new Tower(50f, 18f);    //Tower Constructor, creates texture, life, hit-box of tower
        lifeTxt = new BitmapFont();
        lifeTxt.setColor(Color.WHITE);
        lifeTxt.setFixedWidthGlyphs(".2f");
        background = new Texture("backgrounds/background.png");
        hero = new Hero(280f, 402f);
        weapon = new Weapon(280 + hero.width*1.5f,385f + hero.height/2f);
        enemies = new Array<>();
        paused=false;
        timeOfDeath=0;
        GAME.music = Gdx.audio.newMusic(Gdx.files.internal("Music/in_game.mp3"));
        if(GAME.musicOn){
            GAME.music.setLooping(true);
            GAME.music.play();
        }
    }
    public void spawnEnemy(){
        Enemy enemy = new Enemy(MathUtils.random(0,1),false);
        enemies.add(enemy);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        GAME.camera.update();
        GAME.batch.setProjectionMatrix(GAME.camera.combined);
        GAME.batch.begin();

        GAME.batch.draw(background, 0, 0);
        if (paused){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                paused=false;
                //This calculates the time elapsed while paused in nanoseconds
                pauseTime = TimeUtils.nanoTime() - pauseTime;
            }
            for (Enemy enemy: enemies){
                enemy.stop(GAME.batch,delta);
                enemy.stopSounds();
            }
            hero.stop(GAME.batch,delta);
            weapon.stop(GAME.batch,delta);
            GAME.batch.draw(pauseText, GAME.width/2f - pauseText.getWidth()/2f, GAME.height/2f - pauseText.getHeight()/2f);
            //Go back to main menu pressing ESC
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                this.dispose();
                GAME.score=0;
                GAME.music.stop();
                GAME.music = Gdx.audio.newMusic(Gdx.files.internal("Music/menu.mp3"));
                GAME.setScreen(new MainMenuScreen(GAME));
            }

        }
        else{
            if(GAME.musicOn)
                GAME.music.play();
            if (!hasEnemy){
                spawnEnemy();
                hasEnemy=true;
            }

            for (Enemy enemy: enemies){
                enemy.EnemyMovement(tower);
                if (!enemy.isDead()) {
                    enemy.animate(GAME.batch, 11f);
                }
                else{
                    enemy.animate(GAME.batch, 5f);
                }
                if (weapon.hits(enemy)){
                    enemy.gotHit();
                    if (enemy.isDead() && timeOfDeath ==0){
                        if (enemy.select==0){//archer
                            GAME.score+=5;
                        }
                        else{
                            GAME.score+=10;//rogue knight
                        }
                        timeOfDeath=TimeUtils.millis();
                    }
                }
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
            if (tower.towerLife == 0){
                this.dispose();
                GAME.music.stop();
                for(Enemy en : enemies){
                    en.stopSounds();
                }
                GAME.setScreen(new GameOverScreen(GAME));
            }

            //Pause if SpaceBar is pressed
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                paused=true;
                //Start counting pause time
                pauseTime=TimeUtils.nanoTime();
                GAME.music.pause();
            }

            hero.animate(GAME.batch,11f);
            weapon.draw(GAME.batch, delta);
        }
        lifeTxt.draw(GAME.batch, createStr(tower.towerLife, GAME.score), 250, 660);
        tower.draw(GAME.batch, tower.img);

        GAME.batch.end();
    }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void show() { }
    @Override
    public void hide() { }

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


