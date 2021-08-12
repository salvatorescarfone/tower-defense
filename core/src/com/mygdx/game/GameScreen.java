package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    Hero hero;
    ShapeRenderer shapeRenderer;

    /*Constructor method for the GameScreen*/
    public GameScreen(final MainGame game){

        //draw hitbox borders
        shapeRenderer = new ShapeRenderer();

        this.game=game;
        tower = new Tower();    //Tower Constructor, creates texture, life, hit-box of tower
        lifeTxt = new BitmapFont();
        lifeTxt.setColor(Color.BLACK);
        lifeTxt.setFixedWidthGlyphs(".2f");
        batch = new SpriteBatch();
        background = new Texture("backgrounds/sfondo.jpg");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 675);
        coins = 0;
        enemy = new Enemy(100, false);
        hero = new Hero();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 1, 1, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        lifeTxt.draw(batch, "Life: " + tower.towerLife + " Coins: " + coins, 250, 660);
        batch.draw(tower.img, 0, 0);

        hero.animate(batch,5f);
        enemy.animate(batch, 5f);


        //animate(enemy,"archer_enemy_running.atlas", 10);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);//important!!! without this hitboxes and textures won't be aligned
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawHitbox(hero);
        drawHitbox(enemy);
        drawHitbox(tower);
        shapeRenderer.end();

        enemy.EnemyMovement();

        //controllo collisione
        if (enemy.hitBox.overlaps(tower.hitBox)) {
            enemy.hitBox.x = tower.hitBox.x+tower.hitBox.width;
            enemy.Running();
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
        tower.img.dispose();
        enemy.img.dispose();
        tower.img.dispose();
        hero.textureAtlas.dispose();
        shapeRenderer.dispose();
    }

    /*
    public void animate(Animatable a, String selectAnimationAtlas, float frameRate){
        a.elapsedTime += Gdx.graphics.getDeltaTime();
        a.animate(selectAnimationAtlas,1f/frameRate);
        batch.draw(a.animation.getKeyFrame(a.elapsedTime, true), a.hitBox.x, a.hitBox.y);
    }
     */

    public void drawHitbox(Drawable d){
        shapeRenderer.rect(d.hitBox.x,d.hitBox.y,d.hitBox.width, d.hitBox.height);
    }


}


