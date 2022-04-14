package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameObjects.AbstractGameObjectFactory;
import com.mygdx.game.GameObjects.GameObjectFactory;
import com.mygdx.game.GameObjects.MyButton;
import com.mygdx.game.MainGame.MainGame;

/* This Class is used whenever the player loses a game. Here are shown a Game Over Screen and in this State of the
 * game a new Libgdx Preference is initialized. The Preference is used to save the score of the game locally on the
 * device in use. The user will see the actual score of the game and his previous personal best. Preferences are like
 * java HashMaps, with unique keys and values
 */

public class GameOverScreen implements Screen {
    private MainGame game;
    private AbstractGameObjectFactory creator ;
    private Texture gameOver;
    private Preferences pref;
    private Texture background;
    private MyButton newGameButton;
    private MyButton quitGameButton;
    private int personalBest;
    private int lastScore;
    private float scoreTextWidth;
    private float scoreTextHeight;
    private GlyphLayout actualScoreText;
    private GlyphLayout personalBestText;
    private GlyphLayout lastScoreText;

    public GameOverScreen(){
        creator = new GameObjectFactory();
        this.game = (MainGame)MainGame.getInstance();
        newGameButton= creator.createButton("newgame");
        quitGameButton= creator.createButton("quitgame");
        gameOver = new Texture("GameOverScreen/GameOver.png");
        pref = Gdx.app.getPreferences("highScore");
        background = new Texture("GameOverScreen/Stars.png");
        if (!pref.contains("pb")){
            this.setHighScore();
            personalBest = 0;
        }
        else{
            personalBest = pref.getInteger("pb");
        }
        if (!pref.contains("lastScore")){
            this.setLastScore(game.getScore());
            lastScore = 0;
        }
        else{
            lastScore = pref.getInteger("lastScore");
            this.setLastScore(game.getScore());
        }
        if (game.getScore() > personalBest){
            setHighScore(game.getScore());
        }
        actualScoreText= creator.createText(createStr("Your Score: ", game.getScore()),"white");
        personalBestText =creator.createText(createStr("Previous Personal Best: ",personalBest),"white");
        lastScoreText = creator.createText(createStr("Last Score: ", lastScore), "white");
        scoreTextHeight = game.getHeight() - gameOver.getHeight() - 10f;
        scoreTextWidth = game.getWidth() /2f;


    }
    private String createStr(String str,int score){
        StringBuilder sb= new StringBuilder();
        sb.append(str);
        sb.append(score);
        return sb.toString();
    }

    private void setLastScore(int lastScore){
        pref.putInteger("lastScore",lastScore);
        pref.flush();
    }

    private void setHighScore(){
        pref.putInteger("pb",0);
        pref.flush();
    }
    private void setHighScore(int highScore){
        pref.putInteger("pb",highScore);
        pref.flush();
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getCamera().update();
        game.getBatch().begin();

        game.getBatch().draw(background,0,0, game.getWidth(), game.getHeight());
        game.getBatch().draw(gameOver, game.getWidth() /2f - gameOver.getWidth()/2f, game.getHeight() - gameOver.getHeight(), gameOver.getWidth(),gameOver.getHeight());
        game.getFont().draw(game.getBatch(),actualScoreText,(scoreTextWidth -  actualScoreText.width / 3f), (scoreTextHeight + actualScoreText.height / 3f));
        game.getFont().draw(game.getBatch(),personalBestText,scoreTextWidth - personalBestText.width / 3f,scoreTextHeight - actualScoreText.height -20f - lastScoreText.height + personalBestText.height /3f);
        game.getFont().draw(game.getBatch(),lastScoreText,scoreTextWidth - lastScoreText.width / 3f,scoreTextHeight - actualScoreText.height -10f + lastScoreText.height /3f);

        newGameButton.act(game, this);
        quitGameButton.act(game, this);
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }

        game.getBatch().end();
    }
    @Override
    public void dispose() {
        gameOver.dispose();
        background.dispose();
        quitGameButton.dispose();
        newGameButton.dispose();
    }
    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        this.dispose();
    }
    @Override
    public void show() {
        game.setMusic(Gdx.audio.newMusic(Gdx.files.internal("Music/game_over.wav")));
        game.getMusic().setVolume(0.2f);
        if(game.isMusicOn())
            game.getMusic().play();
    }
}
