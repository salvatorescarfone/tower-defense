package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

/* Main app that contains graphics elements for the game: from design, camera, background
 * and more.
 *
 */

public class MainGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;
	private OrthographicCamera camera;
	private BitmapFont lifeTxt;
	private Tower tower;
	private int coins;
	private Enemy enemy;
	@Override

	public void create () {
		tower = new Tower();	//Tower Constructor, creates texture, life, hit-box of tower
		lifeTxt=new BitmapFont();
		lifeTxt.setColor(Color.BLACK);
		lifeTxt.setFixedWidthGlyphs(".2f");
		batch = new SpriteBatch();
		background = new Texture("sfondo.jpg");
		camera= new OrthographicCamera();
		camera.setToOrtho(false, 1280, 675);
		coins=0;
		enemy=new Enemy(100,false);
	}


	@Override
	public void render () {
		enemy.elapsedTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(1, 1, 1, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, 0, 0);
		lifeTxt.draw(batch, "Life: " + tower.towerLife + " Coins: "+ coins, 250, 660);
		batch.draw(tower.towerImage, 0, 0);
		batch.draw(enemy.animation.getKeyFrame(enemy.elapsedTime,true),enemy.hitBox.x,enemy.hitBox.y);
		batch.end();

		enemy.EnemyMovement();
		if (enemy.hitBox.overlaps(tower.hitBox)){
			enemy.hitBox.x=200;
			if (TimeUtils.nanoTime() - tower.lastDamageTime >=(1000000000/2)){
				tower.DamageTower(enemy.atkPower);
			}

		}

	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		tower.towerImage.dispose();
		enemy.enemyImg.dispose();
	}
}
