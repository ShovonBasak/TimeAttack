package scenes;


import Application.Main;
import UserInterface.ScoreLabel;
import gameObjects.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Random;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.SPACE;

public class LevelOne implements Runnable {
    private Stage window;
    private Scene scene;
    private Group group;
    private Player player;
    private Enemy1[] enemy;
    private Enemy2 enemy2;
    private Coin coin;
    private Random randomPosition;
    private GameOverScene gameOverScene;
    private Main mainMenu;
    private ScoreLabel scoreLabel;
    public static boolean isPaused = false;
    private Thread mainThread;


    public LevelOne(Main mainMenu) {
        this.mainMenu = mainMenu;
        window = new Stage();
        window.setTitle("Time Attack");

        Player.dead = false;
        randomPosition = new Random();

        player = new Player(50, 500, 20);
        player.setSpeed(5);

        Player.levelReached = 1;
        scoreLabel = new ScoreLabel();

        coin = new Coin(28 + randomPosition.nextInt(800), 28 + randomPosition.nextInt(600), 28, player, scoreLabel);

        group = new Group(player);


        enemy = new Enemy1[4];
        enemy[0] = new Enemy1(800, 600, 15, player, coin);
        enemy[0].setSpeed(.2);
        enemy[0].setHorizontalDirection(false);
        enemy[0].setVerticalDirection(true);

        enemy[1] = new Enemy1(800, 600, 15, player, coin);
        enemy[1].setHorizontalDirection(false);
        enemy[1].setSpeed(.2);
        enemy[2] = new Enemy1(800, 600, 15, player, coin);
        enemy[1].setVerticalDirection(true);
        enemy[2].setSpeed(.3);
        enemy[1].setVisible(false);
        enemy[2].setVisible(false);
        enemy[3] = new Enemy1(800, 600, 15, player, coin);
        enemy[3].setVerticalDirection(false);
        enemy[3].setSpeed(.3);
        enemy[3].setVisible(false);


        enemy2 = new Enemy2(100, 100, 15, player, coin);


        group.getChildren().addAll(enemy[0], enemy[1], enemy[2],enemy[3], enemy2, scoreLabel, scoreLabel.getScoreText());

        group.getChildren().addAll(coin, coin.getTimeLabel());


        scene = new Scene(group, 800, 600);

        mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        coin.resume();
        enemy2.resume();
        for(int i=0; i<3; i++){
            enemy[i].resume();
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


    public void run() {
        while (!player.isDead()) {
            Platform.runLater(() -> {
                //do anything
                controlScene();

                if(scoreLabel.getScore() >= 30 && scoreLabel.getScore() < 40){
                    enemy[1].setVisible(true);
                    enemy2.setVisible(true);
                }
                if(scoreLabel.getScore() >= 50 && scoreLabel.getScore() < 60){
                    enemy[2].setVisible(true);
                }
                if(scoreLabel.getScore() > 150 && scoreLabel.getScore() < 160){
                    enemy[3].setVisible(true);

                }
                if(scoreLabel.getScore() >= 120 && scoreLabel.getScore() < 130){
                    Player.levelReached = 2;
                    enemy[0].setSpeed(.2);
                    enemy[1].setSpeed(.2);
                    enemy[2].setSpeed(.5);
                    enemy2.setSpeed(.13);
                }
            });


            try {
                Thread.sleep(1);
            }  catch (Exception ignored) {
            }
        }
        //runs when player is dead
        Platform.runLater(() ->
        {
            gameOverScene = new GameOverScene(mainMenu, scoreLabel);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

