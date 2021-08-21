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
    Texture newGameActive;
    Texture newGameInactive;
    Texture quitGameActive;
    Texture quitGameInactive;
    int personalBest;
    int lastScore;
    float scoreTextWidth;
    float scoreTextHeight;
    final GlyphLayout actualScoreText;
    final GlyphLayout personalBestText;
    final GlyphLayout lastScoreText;
    private final float buttonWidth;
    private final float buttonHeight;
    public GameOverScreen (final MainGame game){
        this.game = game;
        gameOver = new Texture("GameOverScreen/GameOver.png");
        pref = Gdx.app.getPreferences("highScore");
        background = new Texture("GameOverScreen/Stars.png");
        newGameActive = new Texture ("GameOverScreen/NewGame_Active.png");
        newGameInactive = new Texture ("GameOverScreen/NewGame_Inactive.png");
        quitGameActive = new Texture ("GameOverScreen/QuitGame_Active.png");
        quitGameInactive = new Texture("GameOverScreen/QuitGame_Inactive.png");
        buttonWidth = newGameActive.getWidth();
        buttonHeight = newGameActive.getHeight();
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
        actualScoreText= new GlyphLayout(game.font,"Your Score: " + game.score);
        personalBestText = new GlyphLayout(game.font,"Previous Personal Best: " + personalBest);
        lastScoreText = new GlyphLayout(game.font,"Last Score: " + lastScore);
        scoreTextHeight = game.height - gameOver.getHeight() - 10f;
        scoreTextWidth = game.width /2f;
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
        if (Gdx.input.getX() < game.width/2f + buttonWidth/2f && Gdx.input.getX() > game.width/2f - buttonWidth/2f
            && game.height - Gdx.input.getY() < game.height/2f + buttonHeight/2f && game.height - Gdx.input.getY() > game.height/2f - buttonHeight/2f){
            game.batch.draw(newGameActive,game.width/2f - buttonWidth/2f, game.height/2f - buttonHeight/2f, buttonWidth,buttonHeight);
            if (Gdx.input.justTouched()){
                this.dispose();
                game.score=0;
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else{
            game.batch.draw(newGameInactive,game.width/2f - buttonWidth/2f, game.height/2f - buttonHeight/2f, buttonWidth,buttonHeight);
        }
        /*Draw quit Button below new Game Button and set Input response*/
        if (Gdx.input.getX() < game.width/2f + buttonWidth/2f && Gdx.input.getX() > game.width/2f - buttonWidth/2f
            && game.height - Gdx.input.getY() < game.height/2f -10f - buttonHeight/2f && game.height - Gdx.input.getY() > game.height/2f - buttonHeight*1.5f - 10f){
            game.batch.draw(quitGameActive,game.width/2f - buttonWidth/2f, game.height/2f - buttonHeight*1.5f - 10f, buttonWidth,buttonHeight);
            if (Gdx.input.justTouched()){
                this.dispose();
                Gdx.app.exit();
            }
        }
        else {
            game.batch.draw(quitGameInactive,game.width/2f - buttonWidth/2f, game.height/2f - buttonHeight*1.5f - 10f, buttonWidth,buttonHeight);

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
        quitGameActive.dispose();
        quitGameInactive.dispose();
        newGameInactive.dispose();
        newGameActive.dispose();
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
