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
    Array<Enemy> enemies;
    boolean hasEnemy;
    Texture background;
    Texture pauseText;
    BitmapFont lifeTxt;
    Tower tower;
    Hero hero;
    Weapon weapon;
    boolean paused;
    long pauseTime;
    long timeOfDeath;
    /*Constructor method for the GameScreen*/
    public GameScreen(final MainGame game){
        //draw hit box borders
        hasEnemy=false;
        pauseTime=0;
        this.game=game;
        pauseText = new Texture("GameScreen/Pause.png");
        tower = new Tower(50f, 0f);    //Tower Constructor, creates texture, life, hit-box of tower
        lifeTxt = new BitmapFont();
        lifeTxt.setColor(Color.WHITE);
        lifeTxt.setFixedWidthGlyphs(".2f");
        background = new Texture("backgrounds/white.png");
        hero = new Hero(280f, 385f);
        weapon = new Weapon(280 + hero.width*1.5f,385f + hero.height/2f);
        enemies = new Array<>();
        paused=false;
        timeOfDeath=0;
    }
    public void spawnEnemy(){
        Enemy enemy = new Enemy(MathUtils.random(0,1),false);
        enemies.add(enemy);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        //game.batch.draw(background, 0, 0);
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
            weapon.stop(game.batch,delta);
            game.batch.draw(pauseText, game.width/2f - pauseText.getWidth()/2f, game.height/2f - pauseText.getHeight());
        }
        else{
            if (!hasEnemy){
                spawnEnemy();
                hasEnemy=true;
            }

            for (Enemy enemy: enemies){
                enemy.EnemyMovement(tower);
                if (!enemy.isDead()) {
                    enemy.animate(game.batch, 11f);
                }
                else{
                    enemy.animate(game.batch, 5f);
                }
                if (weapon.hits(enemy)){
                    enemy.gotHit();
                    if (enemy.isDead() && timeOfDeath ==0){
                        if (enemy.select==0){
                            game.score+=5;
                        }
                        else{
                            game.score+=10;
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
        lifeTxt.draw(game.batch, createStr(tower.towerLife,game.score), 250, 660);
        tower.draw(game.batch);
        /*Debug code to exit game early*/
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }

        game.batch.end();
        //important!!! without this hit boxes and textures won't be aligned
        /*game.shapeRenderer.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(0,0,0,0);
        drawHitBox(hero.hitBox);
        for (Enemy enemy: enemies){
            drawHitBox(enemy.hitBox);
        }
        drawHitBox(tower.hitBox);
        drawHitBox(weapon.sprite.getBoundingRectangle());
        game.shapeRenderer.end();*/
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

    }
    public void drawHitBox(Rectangle r){
        game.shapeRenderer.rect(r.x,r.y,r.width, r.height);
    }
    private String createStr(int life, int score){
        String str= "Life: ";
        str= str.concat(Integer.toString(life));
        str = str.concat(" Score: ");
        str = str.concat(Integer.toString(score));
        return str;
    }
}


