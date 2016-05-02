package scenes;


import gameObjects.Coin;
import gameObjects.Enemy;
import gameObjects.Enemy1;
import gameObjects.Player;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class LevelOne implements Runnable {
    private Stage window;
    private Scene background;
    private Group group;
    private Player player;
    private Enemy[] enemy;
    private Coin coin;
    private Random randomPosition;

    private Thread mainThread;


    public void show(MainMenu mainMenu) {
        window = new Stage();
        window.setTitle("Time Attack");

        randomPosition = new Random();

        player = new Player(50, 50, 20);
        player.setSpeed(5);

        group = new Group(player);

        enemy = new Enemy[3];
        enemy[0] = new Enemy1(100, 100, 15);
        enemy[0].setSpeed(3);
        enemy[0].setHorizontalDirection(false);
        enemy[0].setVerticalDirection(true);

        enemy[1] = new Enemy1(500, 200, 15);
        enemy[2] = new Enemy1(300, 600, 15);


        group.getChildren().addAll(enemy[0],enemy[1],enemy[2]);

        coin = new Coin(5 + randomPosition.nextInt(500), 5 + randomPosition.nextInt(500), 28);
        group.getChildren().addAll(coin,coin.getTimeLabel());

        background = new Scene(group, 800, 600);

        window.setScene(background);
        window.show();

        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        while ( !player.isDead() ) {
            Platform.runLater(() -> {
                if(coin.isVisible()){
                    for(int i=0; i<3; i++){
                        //For Coin Collides with Enemy
                        if(coin.intersects(enemy[i].getBoundsInLocal())){
                            if (coin.getCenterY() > enemy[i].getCenterY()) {
                                enemy[i].setVerticalDirection(false);
                                coin.moveDown();
                            } else if (coin.getCenterY() < enemy[i].getCenterY()) {
                                enemy[i].setVerticalDirection(true);
                                coin.moveUp();
                            }
                            if (coin.getCenterX() > enemy[i].getCenterX()) {
                                enemy[i].setHorizontalDirection(false);
                                coin.moveRight();
                            } else if (coin.getCenterX() > enemy[i].getCenterX()) {
                                enemy[i].setHorizontalDirection(false);
                                coin.moveLeft();
                            }
                        }
                        //If Player collides with Enemy
                        if(player.intersects(enemy[i].getBoundsInLocal())){
                            player.setDead(true);
                        }
                    }
                }
                else{
                    coin.showCoin();
                }

                //If player Collides with coin
                if(coin.intersects(player.getBoundsInLocal())){
                    coin.hideCoin();
                }
            });
            try{
                Thread.sleep(1);
            } catch (Exception ignored) {}
        }
    }
}


