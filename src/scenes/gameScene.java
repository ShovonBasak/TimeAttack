package scenes;


import gameObjects.Coin;
import gameObjects.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class gameScene {

    public static void show(){
        Stage window = new Stage();
        window.setTitle("Time Attack");

        Player player = new Player(20);
        player.setLayoutX(50);
        player.setLayoutY(50);
        Coin coin = new Coin(100,100,15);


        Group group = new Group(player,coin);


        Scene background = new Scene(group,800,600);

        window.setScene(background);
        window.show();
    }
}
