package scenes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverScene {
    Stage window;
    public Scene scene;
    VBox layout;
    Button restartGame;
    Button Exit;
    Text gameOver;

    public GameOverScene() {
        restartGame = new Button("Restart");
        restartGame.setOnAction(event1 -> {
            //do something
        });
        Exit = new Button("Exit");
        Exit.setOnAction(event -> {
            //do something
        });


        gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameOver.setCache(true);
        gameOver.setFill(Color.PALEVIOLETRED);
        gameOver.setTranslateY(gameOver.getTranslateY() - 10);


    }

    public void show() {
        window = new Stage();

        layout = new VBox(20, gameOver, restartGame, Exit);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600);


        window.setScene(scene);
        window.show();


    }
}
