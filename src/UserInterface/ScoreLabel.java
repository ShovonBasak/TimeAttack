package userInterface;

import gameObjects.Coin;
import gameObjects.Player;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScoreLabel extends Rectangle implements Runnable{
    private int score;
    private Label scoreText;

    public Label getScoreText() {
        return scoreText;
    }

    Thread thisThread;

    Coin coin;

    public ScoreLabel(){
        super(0, 0, 100, 20);
        score = 0;
        this.setFill(Color.LIGHTBLUE);
        scoreText = new Label("Score: "+ score);
        scoreText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        scoreText.setTextFill(Paint.valueOf("white"));

        thisThread = new Thread(this);
        thisThread.start();
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setScorePosition(){
        this.scoreText.setAlignment(Pos.TOP_LEFT);
    }

    @Override
    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {
                scoreText.setText("Score: "+ score);
            });

            try {
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        }
    }
}
