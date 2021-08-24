package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/* This is the Screen where the core of the game resides. In here all "actors" such as hero, enemies and the tower
 * are drawn on the scene and act in a particular way. The hero is piloted by the user while the enemies are randomly
 * spawned on screen.
 */

public class GameScreen implements Screen {
    final MainGame game;
    long lastEnemySpawn;
    private static final int maxEnemyOnScreen = 3;
    int enemyCount;
    Array<Enemy> enemies;
    Texture background;
    Texture pauseText;
    BitmapFont lifeTxt;
    Tower tower;
    int coins;
    Hero hero;
    Weapon weapon;
    boolean paused;
    long pauseTime;
    /*Constructor method for the GameScreen*/
    public GameScreen(final MainGame game){
        //draw hit box borders
        enemyCount=0;
        pauseTime=0;
        this.game=game;
        pauseText = new Texture("GameScreen/Pause.png");
        tower = new Tower(50f, 0f);    //Tower Constructor, creates texture, life, hit-box of tower
        lifeTxt = new BitmapFont();
        lifeTxt.setColor(Color.BLACK);
        lifeTxt.setFixedWidthGlyphs(".2f");
        background = new Texture("backgrounds/white.png");
        coins = 0;
        hero = new Hero(280f, 385f);
        weapon = new Weapon(280 + hero.width*1.5f,385f + hero.height/2f);
        enemies = new Array<>();
        spawnEnemy();
        paused=false;
        game.score=MathUtils.random(0,100);
    }
    public void spawnEnemy(){
        Enemy enemy = new Enemy(100,false,Gdx.graphics.getWidth(),0);
        enemies.add(enemy);
        enemyCount++;
        lastEnemySpawn = TimeUtils.nanoTime();
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        if (paused){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                paused=false;
                //This calculates the time elapsed while paused in nanoseconds
                pauseTime = TimeUtils.nanoTime() - pauseTime;
            }
            for (Enemy enemy: enemies){
                enemy.stop(game.batch,delta);
            }
            hero.stop(game.batch,delta);
            game.batch.draw(pauseText, game.width/2f - pauseText.getWidth()/2f, game.height/2f - pauseText.getHeight());
        }
        else{
            for (Enemy enemy: enemies){
                enemy.EnemyMovement(tower);
                enemy.animate(game.batch,11f);

            }
            if (TimeUtils.nanoTime() - lastEnemySpawn - pauseTime > 3000000000L && enemyCount < maxEnemyOnScreen){
                spawnEnemy();
                pauseTime=0;
            }
            if (tower.towerLife == 0){
                this.dispose();
                game.setScreen(new GameOverScreen(game));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                paused=true;
                //Start counting pause time
                pauseTime=TimeUtils.nanoTime();
            }
            hero.animate(game.batch,11f);
            weapon.draw(game.batch, delta);
        }
        lifeTxt.draw(game.batch, createStr(tower.towerLife,coins,game.score), 250, 660);
        tower.draw(game.batch);
        /*Debug code to exit game early*/
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }

        game.batch.end();
        //important!!! without this hit boxes and textures won't be aligned
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(0,0,0,0);
        drawHitBox(hero.hitBox);
        for (Enemy enemy: enemies){
            drawHitBox(enemy.hitBox);
        }
        drawHitBox(tower.hitBox);
        drawHitBox(weapon.sprite.getBoundingRectangle());
        game.shapeRenderer.end();
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
        for (Iterator<Enemy> i = enemies.iterator(); i.hasNext(); ) {
            Enemy enemy = i.next();
            i.remove();
            }
    }
    public void drawHitBox(Rectangle r){
        game.shapeRenderer.rect(r.x,r.y,r.width, r.height);
    }
    /*
    public void animate(Animatable a, String selectAnimationAtlas, float frameRate){
        a.elapsedTime += Gdx.graphics.getDeltaTime();
        a.animate(selectAnimationAtlas,1f/frameRate);
        batch.draw(a.animation.getKeyFrame(a.elapsedTime, true), a.hitBox.x, a.hitBox.y);
    }
     */
    private String createStr(int life, int coins, int score){
        String str= "Life: ";
        str= str.concat(Integer.toString(life));
        str= str.concat(" Coins: ");
        str = str.concat(Integer.toString(coins));
        str = str.concat(" Score: ");
        str = str.concat(Integer.toString(score));
        return str;
    }
}


