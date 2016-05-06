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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    ArrayList<Enemy> enemies;
    public static boolean isPaused = false;
    private Thread mainThread;
    private Text pauseText;


    public GameScene(Main mainMenu) {
        pauseText=new Text("Paused");
        pauseText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        pauseText.setFill(Paint.valueOf("BLUE"));
        pauseText.setVisible(false);





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


        group.getChildren().addAll(scoreLabel, scoreLabel.getScoreText(), levelLable,pauseText);

        group.getChildren().addAll(coin, coin.getTimeLabel());



        scene = new Scene(group, Color.web("#00ff99",.30));



        levelLable.setTextAlignment(TextAlignment.CENTER);
        levelLable.setX(getScene().getWidth() / 2 - 55);
        levelLable.setY(levelLable.getTranslateY() + 30);

        mainMenu.getWindow().resizableProperty().setValue(true);
        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        coin.resume();
        enemies.forEach(Enemy::resume);
    }


    public void controlScene(){
        getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ENTER){
                pauseText.setVisible(false);
                resume();
            }
            if(e.getCode() == SPACE){
                isPaused = true;
                pauseText.setVisible(true);
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


            for(Enemy enemy:enemies){
                enemy.setSpeed(enemy.getSpeed()+.2);
            }

            if(level % 3 == 0 || level == 1 ){
                Enemy1 enemy = new Enemy1(0, 0, 10, player, coin);
                enemies.add(enemy);
                group.getChildren().addAll(enemy);
                enemy.setSpeed(1);
                enemyCounter++;
            }
            if(level == 5){
                Enemy enemy = new Enemy2(1024, 0, 10, player, coin);
                enemy.setSpeed(1);
                enemies.add(enemy);
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
                pauseText.setLayoutX(getScene().getWindow().getWidth()/2-30);
                pauseText.setLayoutY(getScene().getWindow().getHeight()/2-30);
                levelLable.setText("Level:" + String.valueOf(level));
                levelLable.setLayoutX(getScene().getWindow().getWidth()/2-30);
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

