package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

/* This Class is used whenever the player loses a game. Here are shown a Game Over Screen and in this State of the
 * game a new Libgdx Preference is initialized. The Preference is used to save the score of the game locally on the
 * device in use. The user will see the actual score of the game and his previous personal best. Preferences are like
 * java HashMaps, with unique keys and values
 */

public class GameOverScreen implements Screen {
    final MainGame game;
    Texture gameOver;
    Preferences pref;
    Texture background;
    MyButton newGameButton;
    MyButton quitGameButton;
    int personalBest;
    int lastScore;
    float scoreTextWidth;
    float scoreTextHeight;
    final GlyphLayout actualScoreText;
    final GlyphLayout personalBestText;
    final GlyphLayout lastScoreText;
    private static final float BUTTON_WIDTH=150f;
    private static final float BUTTON_HEIGHT=50f;
    public GameOverScreen (final MainGame game){
        this.game = game;
        newGameButton= new MyButton("GameOverScreen/NewGame_Active.png","GameOverScreen/NewGame_Inactive.png",
                game.width/2f - BUTTON_WIDTH/2f, game.height/2f - BUTTON_HEIGHT/2f, BUTTON_WIDTH,BUTTON_HEIGHT,
                game.width, game.height);
        quitGameButton= new MyButton("GameOverScreen/QuitGame_Active.png", "GameOverScreen/QuitGame_Inactive.png",
                game.width/2f - BUTTON_WIDTH/2f, game.height/2f - BUTTON_HEIGHT*1.5f - 10f, BUTTON_WIDTH,
                BUTTON_HEIGHT, game.width, game.height);
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
            this.setLastScore(game.score);
            lastScore = 0;
        }
        else{
            lastScore = pref.getInteger("lastScore");
            //Set This current score as lastScore for next game
            this.setLastScore(game.score);
        }
        if (game.score > personalBest){
            setHighScore(game.score);
        }
        game.font.setColor(Color.WHITE);
        actualScoreText= new GlyphLayout(game.font,createStr("Your Score: ",game.score));
        personalBestText = new GlyphLayout(game.font,createStr("Previous Personal Best: ",personalBest));
        lastScoreText = new GlyphLayout(game.font,createStr("Last Score: ",lastScore));
        scoreTextHeight = game.height - gameOver.getHeight() - 10f;
        scoreTextWidth = game.width /2f;
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
    /*Used if a personal best was never set before (first game on device)*/
    private void setHighScore(){
        pref.putInteger("pb",0);
        pref.flush();       //flush method makes changes in pref definitive
    }
    /*Used to overwrite the score in case the user made a new high score*/
    private void setHighScore(int highScore){
        pref.putInteger("pb",highScore);
        pref.flush();
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();
        game.batch.draw(background,0,0,game.width,game.height);
        game.batch.draw(gameOver, game.width/2f - gameOver.getWidth()/2f, game.height - gameOver.getHeight(), gameOver.getWidth(),gameOver.getHeight());
        game.font.draw(game.batch,actualScoreText,(scoreTextWidth -  actualScoreText.width / 3f), (scoreTextHeight + actualScoreText.height / 3f));
        game.font.draw(game.batch,personalBestText,scoreTextWidth - personalBestText.width / 3f,scoreTextHeight - actualScoreText.height -20f - lastScoreText.height + personalBestText.height /3f);
        game.font.draw(game.batch,lastScoreText,scoreTextWidth - lastScoreText.width / 3f,scoreTextHeight - actualScoreText.height -10f + lastScoreText.height /3f);
        /*Draw new Game Button Centered on the screen and set Input response*/
        newGameButton.draw(game.batch);
        if (newGameButton.isActive() && Gdx.input.justTouched()){
            this.dispose();
            game.score=0;
            game.setScreen(new MainMenuScreen(game));
        }
        /*Draw quit Button below new Game Button and set Input response*/
        quitGameButton.draw(game.batch);
        if (quitGameButton.isActive() && Gdx.input.justTouched()){
            if (Gdx.input.justTouched()){
                this.dispose();
                Gdx.app.exit();
            }
        }
        /*Debug to exit game*/
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)){
            Gdx.app.exit();
        }
        game.batch.end();
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
    public void hide() { }
    @Override
    public void show() { }
}
