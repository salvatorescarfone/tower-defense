package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final MainGame game;


    /*Things that were initially in MainGame*/
    SpriteBatch batch;
    Texture background;
    OrthographicCamera camera;
    BitmapFont lifeTxt;
    Tower tower;
    int coins;
    Enemy enemy;
    MainCharacter hero;

    /*Constructor method for the GameScreen*/
    public GameScreen(final MainGame game){

        this.game=game;
        tower = new Tower();    //Tower Constructor, creates texture, life, hit-box of tower
        lifeTxt = new BitmapFont();
        lifeTxt.setColor(Color.BLACK);
        lifeTxt.setFixedWidthGlyphs(".2f");
        batch = new SpriteBatch();
        background = new Texture("sfondo.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 675);
        coins = 0;
        enemy = new Enemy(100, false);
        hero = new MainCharacter();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        enemy.elapsedTime += Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(1, 1, 1, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        lifeTxt.draw(batch, "Life: " + tower.towerLife + " Coins: " + coins, 250, 660);
        batch.draw(tower.towerImage, 0, 0);

        batch.draw(enemy.animation.getKeyFrame(enemy.elapsedTime, true), enemy.hitBox.x, enemy.hitBox.y);
        animate(hero,"hero_idle.atlas", 5);//5 fps

        batch.end();

        enemy.EnemyMovement();

        //controllo collisione
        if (enemy.hitBox.overlaps(tower.hitBox)) {
            enemy.hitBox.x = 200;
            if (TimeUtils.nanoTime() - tower.lastDamageTime >= (1000000000 / 2)) {
                tower.DamageTower(enemy.atkPower);
            }
        }
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
        batch.dispose();
        background.dispose();
        tower.towerImage.dispose();
        enemy.enemyImg.dispose();
        hero.textureAtlas.dispose();
    }


    public void animate(Animatable a, String selectAnimationAtlas, float frameRate){
        a.elapsedTime += Gdx.graphics.getDeltaTime();
        a.animate(selectAnimationAtlas, 1f/frameRate);
        batch.draw(a.animation.getKeyFrame(a.elapsedTime, true), a.hitBox.x, a.hitBox.y);
    }

}


