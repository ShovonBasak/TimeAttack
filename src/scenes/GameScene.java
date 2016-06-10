package scenes;


import UserInterface.ScoreLabel;
import Application.Main;
import gameObjects.*;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.SPACE;

public class GameScene implements Runnable {
    private Scene scene;
    private Group group;
    private Player player;
    private Coin coin;
    private GameOverScene gameOverScene;
    private Main mainMenu;
    private ScoreLabel scoreLabel;
    private Text levelLabel;
    private int level;
    private int scoreLevelCounter;
    private ArrayList<Enemy> enemies;
    public static boolean isPaused = false;
    private Text pauseText;
    public MediaPlayer mediaPlayer;

    public GameScene(Main mainMenu) {
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//src//Resources//AudioClip//GameBGM.mp3");



            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        pauseText=new Text("Paused");
        pauseText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        pauseText.setFill(Paint.valueOf("BLUE"));
        pauseText.setVisible(false);


        level = 0;
        scoreLevelCounter = 50;

        levelLabel = new Text("Level:" + String.valueOf(level));
        levelLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        levelLabel.setFill(Paint.valueOf("RED"));

        enemies = new ArrayList<>();

        this.mainMenu = mainMenu;

        Player.dead = false;
        Random randomPosition = new Random();

        player = new Player(50, 500, 30);

        scoreLabel = new ScoreLabel();

        coin = new Coin(28 + randomPosition.nextInt(800), 28 + randomPosition.nextInt(600), 35, player, scoreLabel);

        group = new Group(player);
        group.getChildren().addAll(scoreLabel, scoreLabel.getScoreText(), levelLabel, pauseText);
        group.getChildren().addAll(coin.getCoin());

        scene = new Scene(group,800,600,Color.CYAN);
        scene.setCursor(Cursor.NONE);


        levelLabel.setTextAlignment(TextAlignment.CENTER);
        levelLabel.setX(getScene().getWidth() / 2 - 55);
        levelLabel.setY(levelLabel.getTranslateY() + 30);

        mainMenu.getWindow().resizableProperty().setValue(true);
        Thread mainThread = new Thread(this);
        mainThread.start();
    }

    private synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        coin.resume();
        enemies.forEach(Enemy::resume);
    }


    private void controlScene(){
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

    private void checkLevel() {
        if (scoreLabel.getScore() >= scoreLevelCounter && level < level+1) {
            level++;
            scoreLevelCounter += 50;


            for(Enemy enemy:enemies){
                enemy.setSpeed(enemy.getSpeed()+.2);
            }

            if(level % 3 == 0 || level == 1 ){
                Enemy1 enemy = new Enemy1(0, 0, 35, player, coin);
                enemies.add(enemy);
                group.getChildren().addAll(enemy);
                enemy.setSpeed(1);

            }
            if(level == 5){
                Enemy enemy = new Enemy2(1024, 0, 35, player, coin);
                enemy.setSpeed(1);
                enemies.add(enemy);
                group.getChildren().addAll(enemy);
            }
        }
    }

    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {
                //do anything
                controlScene();
                checkLevel();
                pauseText.setLayoutX(getScene().getWindow().getWidth()/2-30);
                pauseText.setLayoutY(getScene().getWindow().getHeight()/2-30);
                levelLabel.setText("Level:" + String.valueOf(level));
                levelLabel.setLayoutX(getScene().getWindow().getWidth()/2-400);

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
            mediaPlayer.stop();
            gameOverScene = new GameOverScene(mainMenu, scoreLabel, level);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

