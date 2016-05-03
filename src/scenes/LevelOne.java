package scenes;


import Application.Main;
import gameObjects.Coin;
import gameObjects.Enemy1;
import gameObjects.Player;
import gameObjects.ScoreLabel;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class LevelOne implements Runnable {
    private Stage window;
    private Scene scene;
    private Group group;
    private Player player;
    private Enemy1[] enemy;
    private Coin coin;
    private Random randomPosition;
    GameOverScene gameOverScene;
    Main mainMenu;
    ScoreLabel scoreLabel;
    private Thread mainThread;


    public LevelOne(Main mainMenu) {
        this.mainMenu = mainMenu;
        window = new Stage();
        window.setTitle("Time Attack");

        Player.dead = false;
        randomPosition = new Random();

        player = new Player(50, 500, 20);
        player.setSpeed(5);


        scoreLabel = new ScoreLabel();

        coin = new Coin(28 + randomPosition.nextInt(800), 28 + randomPosition.nextInt(600), 28, player, scoreLabel);

        group = new Group(player);

        enemy = new Enemy1[3];
        enemy[0] = new Enemy1(800, 600, 15, player, coin);
        enemy[0].setSpeed(.1);
        enemy[0].setHorizontalDirection(false);
        enemy[0].setVerticalDirection(true);

        enemy[1] = new Enemy1(800, 600, 15, player, coin);
        enemy[1].setVerticalDirection(false);
        enemy[1].setSpeed(.6);
        enemy[2] = new Enemy1(800, 600, 15, player, coin);
        enemy[1].setVerticalDirection(true);
        enemy[2].setSpeed(.6);


        group.getChildren().addAll(enemy[0],enemy[1],enemy[2],scoreLabel, scoreLabel.getScoreText());

        group.getChildren().addAll(coin,coin.getTimeLabel());

        scene = new Scene(group, 800, 600);


        gameOverScene = new GameOverScene(mainMenu);

        mainThread = new Thread(this);
        mainThread.start();
    }

    public Scene getScene() {
        return this.scene;
    }


    public void run() {
        while (!player.isDead()) {
            Platform.runLater(() -> {
                //do anything
            });

            try {
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        }

        //runs when player is dead
        Platform.runLater(() -> mainMenu.getWindow().setScene(gameOverScene.getScene()));
    }
}


