package scenes;


import gameObjects.Enemy1;
import gameObjects.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScene{
    public Stage window;
    Scene background;
    Group group;
    Player player;
    Enemy1 enemy;
    Enemy1 enemy1;
    Text text;
    Thread enemyThread;


    public void show(){
        window = new Stage();
        window.setTitle("Time Attack");

        player = new Player(20);
        player.setLayoutX(50);
        player.setLayoutY(50);

        enemy = new Enemy1(100, 100, 15, this);
        enemy.setSpeed(3);
        enemy.setHorizontalDirection(true);
        enemy.setVerticalDirection(true);

        enemy1 = new Enemy1(300, 50, 15, this);
        enemy1.setSpeed(2);
        enemy1.setHorizontalDirection(false);
        enemy1.setVerticalDirection(false);

        group = new Group(player,enemy,enemy1);

        background = new Scene(group,800,600);

        window.setScene(background);
        window.show();
    }
}
