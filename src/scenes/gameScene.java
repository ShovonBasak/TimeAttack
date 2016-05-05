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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.SPACE;

public class GameScene implements Runnable {
    private Scene scene;
    private Group group;
    private Player player;
    private Coin coin;
    private Random randomPosition;
    private GameOverScene gameOverScene;
    private Main mainMenu;
    private ScoreLabel scoreLabel;
    private Text levelLable;
    private int level;
    private int enemyCounter;
    private int scoreLevelCounter;
    ArrayList<Enemy1> enemies;
    public static boolean isPaused = false;
    private Thread mainThread;


    public GameScene(Main mainMenu) {
        enemyCounter = 0;
        level = 0;
        scoreLevelCounter = 50;


        levelLable = new Text("Level:" + String.valueOf(level));
        levelLable.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        levelLable.setFill(Paint.valueOf("RED"));


        enemies = new ArrayList<>();

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
        for(Enemy enemy:enemies){
            enemy.resume();
        }
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
        if (scoreLabel.getScore() >= scoreLevelCounter && level < level+1) {
            level++;
            scoreLevelCounter += 50;


            for(Enemy1 enemy:enemies){
                enemy.setSpeed(enemy.getSpeed()+.2);
            }

            if(enemyCounter < 3){
                Enemy1 enemy = new Enemy1(0, 0, 10, player, coin);
                enemies.add(enemy);
                group.getChildren().addAll(enemy);
                enemy.setSpeed(1);
                enemyCounter++;
            }
            if(level == 5){
                Enemy enemy = new Enemy2(800, 0, 10, player, coin);
                enemy.setSpeed(1);
                group.getChildren().addAll(enemy);
            }
        }
    }

    public void run() {
        while (!player.isDead()) {
            Platform.runLater(() -> {
                //do anything
                controlScene();
                checkLevel();

                levelLable.setText("Level:" + String.valueOf(level));
            });


            try {
                Thread.sleep(30);
            }  catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        //runs when player is dead
        Platform.runLater(() ->
        {
            gameOverScene = new GameOverScene(mainMenu, scoreLabel, level);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

