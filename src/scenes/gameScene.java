package scenes;


import Application.Main;
import UserInterface.ScoreLabel;
import gameObjects.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.SPACE;

public class gameScene implements Runnable {
    private Scene scene;
    private Group group;
    private Player player;
    private Coin coin;
    private Random randomPosition;
    private GameOverScene gameOverScene;
    private Main mainMenu;
    private ScoreLabel scoreLabel;
    private Text levelLable;
    private int Level;
    private int enemyCounter;
    public static boolean isPaused = false;
    private Thread mainThread;


    public gameScene(Main mainMenu) {
        enemyCounter = 0;
        Level = 1;


        levelLable = new Text("Level:" + String.valueOf(Level));
        levelLable.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        levelLable.setFill(Paint.valueOf("RED"));




        this.mainMenu = mainMenu;

        Player.dead = false;
        randomPosition = new Random();

        player = new Player(50, 500, 20);

        scoreLabel = new ScoreLabel();

        coin = new Coin(28 + randomPosition.nextInt(800), 28 + randomPosition.nextInt(600), 28, player, scoreLabel);

        group = new Group(player);


        group.getChildren().addAll(scoreLabel, scoreLabel.getScoreText(), levelLable);

        group.getChildren().addAll(coin, coin.getTimeLabel());


        scene = new Scene(group, 800, 600);

        levelLable.setX(getScene().getWidth() / 2 - 55);
        levelLable.setY(levelLable.getTranslateY() + 30);

        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        coin.resume();


    }


    public void controlScene(){
        getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ENTER){
                resume();
            }
            if(e.getCode() == SPACE){
                isPaused = true;
            }
        });
    }

    public Scene getScene() {
        return this.scene;
    }

    public void checkLevel() {
        if (scoreLabel.getScore() >= 50 && Level < 2) {
            Level++;
        }

        if (scoreLabel.getScore() >= 100 && Level < 3) {
            Level++;
        }

        if (scoreLabel.getScore() >= 150 && Level < 4) {
            Level++;
        }

        if (scoreLabel.getScore() >= 200 && Level < 5) {
            Level++;
        }

        if (scoreLabel.getScore() >= 250 && Level < 6) {
            Level++;
        }

        if (scoreLabel.getScore() >= 300 && Level < 7) {
            Level++;
        }

        if (scoreLabel.getScore() >= 400 && Level < 8) {
            Level++;
        }

        if (scoreLabel.getScore() >= 500 && Level < 9) {
            Level++;
        }

        if (scoreLabel.getScore() >= 550 && Level < 10) {
            Level++;
        }


    }

    public void run() {
        while (!player.isDead()) {
            Platform.runLater(() -> {
                //do anything
                controlScene();
                checkLevel();
                if (Level == 2 && enemyCounter < 2) {
                    Enemy enemy = new Enemy1(0, 0, 10, player, coin);
                    enemy.setSpeed(.4);
                    enemyCounter++;
                    group.getChildren().addAll(enemy);
                }

                if (Level == 3 && enemyCounter < 3) {
                    Enemy enemy = new Enemy1(0, 0, 10, player, coin);
                    enemy.setSpeed(.4);
                    enemyCounter++;
                    group.getChildren().addAll(enemy);
                }

                if (Level == 4 && enemyCounter < 4) {
                    Enemy enemy = new Enemy1(0, 0, 10, player, coin);
                    enemy.setSpeed(.4);
                    enemyCounter++;
                    group.getChildren().addAll(enemy);
                }

                if (Level == 5 && enemyCounter < 5) {
                    Enemy enemy = new Enemy2(0, 0, 10, player, coin);
                    enemy.setSpeed(.2);
                    enemyCounter++;
                    group.getChildren().addAll(enemy);
                }


                levelLable.setText("Level:" + String.valueOf(Level));

            });


            try {
                Thread.sleep(1);
            }  catch (Exception ignored) {
            }
        }
        //runs when player is dead
        Platform.runLater(() ->
        {
            gameOverScene = new GameOverScene(mainMenu, scoreLabel, Level);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

