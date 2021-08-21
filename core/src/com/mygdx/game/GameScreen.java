package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    final MainGame game;
    String[] enemiesAtlases = {"characters/archer/archer_running.atlas"};
    long lastEnemySpawn;
    private static final int maxEnemyOnScreen = 3;
    int enemyCount;
    Array<Enemy> enemies;
    Texture background;
    BitmapFont lifeTxt;
    Tower tower;
    int coins;
    Hero hero;

    /*Constructor method for the GameScreen*/
    public GameScreen(final MainGame game){

        //draw hit box borders
        enemyCount=0;
        this.game=game;
        tower = new Tower(50f, 0f);    //Tower Constructor, creates texture, life, hit-box of tower
        lifeTxt = new BitmapFont();
        lifeTxt.setColor(Color.BLACK);
        lifeTxt.setFixedWidthGlyphs(".2f");
        background = new Texture("backgrounds/white.png");
        coins = 0;
        hero = new Hero(280f, 385f);
        enemies = new Array<>();
        spawnEnemy();
    }

    public void spawnEnemy(){
        int enemySelect = MathUtils.random(0,0);
        Enemy enemy = new Enemy(100,false,Gdx.graphics.getWidth(),0);
        enemies.add(enemy);
        enemyCount++;
        lastEnemySpawn = TimeUtils.nanoTime();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 1, 1, 0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        lifeTxt.draw(game.batch, "Life: " + tower.towerLife + " Coins: " + coins + " Score: " + game.score, 250, 660);
        tower.draw(game.batch);
        hero.animate(game.batch,11f);
        for (Enemy enemy : enemies){
            enemy.animate(game.batch,11f);
            enemy.EnemyMovement(tower);
        }
        if (TimeUtils.nanoTime() - lastEnemySpawn > 3000000000L && enemyCount < maxEnemyOnScreen){
            spawnEnemy();
        }
        /*Debug code to exit game early*/
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }
        if (tower.towerLife == 0){
            this.dispose();
            game.setScreen(new GameOverScreen(game));
        }

        game.batch.end();
        //important!!! without this hit boxes and textures won't be aligned
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(0,0,0,0);
        drawHitBox(hero);
        for (Enemy enemy: enemies){
            drawHitBox(enemy);
        }
        drawHitBox(tower);
        game.shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        for (Iterator<Enemy> i = enemies.iterator(); i.hasNext(); ) {
            Enemy enemy = i.next();
            i.remove();
            }
    }

    /*
    public void animate(Animatable a, String selectAnimationAtlas, float frameRate){
        a.elapsedTime += Gdx.graphics.getDeltaTime();
        a.animate(selectAnimationAtlas,1f/frameRate);
        batch.draw(a.animation.getKeyFrame(a.elapsedTime, true), a.hitBox.x, a.hitBox.y);
    }
     */

    public void drawHitBox(Drawable d){
        game.shapeRenderer.rect(d.hitBox.x,d.hitBox.y,d.hitBox.width, d.hitBox.height);
    }


}


