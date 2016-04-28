package scenes;


import gameObjects.Coin;
import gameObjects.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class gameScene {

    public static void show(){
        Stage window = new Stage();

        Player player = new Player(10);
        Coin coin = new Coin(5);

        Group group = new Group(player,coin);


        Scene background = new Scene(group,800,600);

        window.setScene(background);
        window.show();
    }
}
