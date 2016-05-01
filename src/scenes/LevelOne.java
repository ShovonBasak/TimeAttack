package scenes;


import gameObjects.Coin;
import gameObjects.Enemy1;
import gameObjects.Player;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelOne implements Runnable {
    public Stage window;
    private Scene background;
    public Group group;
    public Player player;
    private Enemy1 enemy;
    private Coin coin;

    private Thread mainThread;

    public void show() {
        window = new Stage();
        window.setTitle("Time Attack");

        player = new Player(50, 50, 20);
        player.setSpeed(5);

        group = new Group(player);

        enemy = new Enemy1(100, 100, 15);
        enemy.setSpeed(3);
        enemy.setHorizontalDirection(false);
        enemy.setVerticalDirection(true);
        group.getChildren().add(enemy);

        coin = new Coin(500, 300, 20);
        group.getChildren().addAll(coin,coin.getTimeLabel());

        background = new Scene(group, 800, 600);

        window.setScene(background);
        window.show();

        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        while (!player.isDead()) {
            Platform.runLater(() -> {
                if(coin.isVisible()) {
                    //If enemy Collides with coin
                    if (enemy.intersects(coin.getBoundsInLocal())) {
                        if (coin.getCenterY() > enemy.getCenterY()) {
                            enemy.setVerticalDirection(false);
                            coin.moveDown();
                        } else if (coin.getCenterY() < enemy.getCenterY()) {
                            enemy.setVerticalDirection(true);
                            coin.moveUp();
                        }
                        if (coin.getCenterX() > enemy.getCenterX()) {
                            enemy.setHorizontalDirection(false);
                            coin.moveRight();
                        } else if (coin.getCenterX() > enemy.getCenterX()) {
                            enemy.setHorizontalDirection(false);
                            coin.moveLeft();
                        }
                    }
                    //If player Collides with coin
                    if (player.intersects(coin.getBoundsInLocal())){
                        coin.setVisible(false);
                        coin.getTimeLabel().setVisible(false);
                    }
                }
                //If Player Collides with enemy
                if(player.isVisible()) {
                    if (player.intersects(enemy.getBoundsInLocal())) {
                        player.setDead(true);
                        player.setVisible(false);
                    }
                }
            });
            try{
                mainThread.sleep(1);
            } catch (Exception e) {}
        }
    }
}


