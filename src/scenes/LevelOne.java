package scenes;


import Application.Main;
import gameObjects.Coin;
import gameObjects.Enemy1;
import gameObjects.Player;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Random;

public class LevelOne implements Runnable {
    private Stage window;
    private Scene background;
    private Group group;
    private Player player;
    private Enemy1[] enemy;
    private Coin coin;
    private Random randomPosition;
    private Rectangle2D primaryScreenBounds;
    GameOverScene gameOverScene;

    private Thread mainThread;

    public void show(Main mainMenu) {
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        window = new Stage();
        window.setTitle("Time Attack");
        window.setX(primaryScreenBounds.getMinX());
        window.setY(primaryScreenBounds.getMinY());
        window.setMinWidth(primaryScreenBounds.getMaxX());
        window.setMinHeight(primaryScreenBounds.getMaxY());

        randomPosition = new Random();

        player = new Player(50, 500, 20);
        player.setSpeed(5);

        group = new Group(player);

        enemy = new Enemy1[3];
        enemy[0] = new Enemy1(1300, 600, 15);
        enemy[0].setSpeed(.3);
        enemy[0].setHorizontalDirection(false);
        enemy[0].setVerticalDirection(true);

        enemy[1] = new Enemy1(1300, 600, 15);
        enemy[1].setVerticalDirection(false);
        enemy[2] = new Enemy1(1300, 600, 15);
        enemy[1].setVerticalDirection(true);


        group.getChildren().addAll(enemy[0],enemy[1],enemy[2]);

        coin = new Coin(28 + randomPosition.nextInt(1200), 28 + randomPosition.nextInt(500), 28);
        group.getChildren().addAll(coin,coin.getTimeLabel());

        background = new Scene(group);

        window.setScene(background);
        window.show();

        gameOverScene = new GameOverScene();

        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        while ( !Player.dead ) {
            Platform.runLater(() -> {
                if(coin.isVisible() && !Player.dead){
                    for(int i=0; i<3; i++){
                        //If Coin Collides with Enemy
                        if(!Player.dead && coin.getBoundsInLocal().intersects(enemy[i].getBoundsInLocal())){
                            coin.collides(enemy[i]);
                        }
                        //If Player collides with Enemy
                        if(!Player.dead && player.getBoundsInLocal().intersects(enemy[i].getBoundsInLocal())){
                            Player.dead = true;
                            window.close();
                            gameOverScene.show();
                            break;
                        }
                    }
                }

                //If player Collides with coin
                if(!Player.dead){
                    if(coin.getBoundsInLocal().intersects(player.getBoundsInLocal())){
                        coin.hideCoin();
                        coin.setTimeAndPosition(0,15);
                    }
                }
            });
            try{
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        }
    }
}


