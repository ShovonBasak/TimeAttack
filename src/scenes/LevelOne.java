package scenes;


import gameObjects.Coin;
import gameObjects.Enemy;
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
    private Enemy[] enemy;
    private Coin[] coin;

    private Thread mainThread;

    public void show() {
        window = new Stage();
        window.setTitle("Time Attack");

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

        coin = new Coin[3];
        coin[0] = new Coin(500, 300, 20);
        coin[1] = new Coin(350, 100, 20);
        coin[2] = new Coin(300, 500, 20);
        group.getChildren().addAll(coin[0],coin[0].getTimeLabel());
        group.getChildren().addAll(coin[1],coin[1].getTimeLabel());
        group.getChildren().addAll(coin[2],coin[2].getTimeLabel());

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
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if(coin[j].isVisible()){
                            if (enemy[i].intersects(coin[j].getBoundsInLocal())) {
                                if (coin[j].getCenterY() > enemy[i].getCenterY()) {
                                    enemy[i].setVerticalDirection(false);
                                    coin[j].moveDown();
                                } else if (coin[j].getCenterY() < enemy[i].getCenterY()) {
                                    enemy[i].setVerticalDirection(true);
                                    coin[j].moveUp();
                                }
                                if (coin[j].getCenterX() > enemy[i].getCenterX()) {
                                    enemy[i].setHorizontalDirection(false);
                                    coin[j].moveRight();
                                } else if (coin[j].getCenterX() > enemy[i].getCenterX()) {
                                    enemy[i].setHorizontalDirection(false);
                                    coin[j].moveLeft();
                                }
                            }
                        }
                    }
                    //If Player Collides with enemy
                    if (player.intersects(enemy[i].getBoundsInLocal())) {
                        player.setDead(true);
                        player.setVisible(false);
                    }
                }




                    //If player Collides with coin
                    /*if (player.intersects(coin.getBoundsInLocal())){
                        coin.setVisible(false);
                        coin.getTimeLabel().setVisible(false);
                    }*/
                //}
            });
            try{
                mainThread.sleep(1);
            } catch (Exception e) {}
        }
    }
}


