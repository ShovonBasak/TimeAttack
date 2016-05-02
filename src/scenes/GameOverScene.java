package scenes;


import Application.Main;
import Application.ScoreBoard;
import UserInterface.CustomButton;
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
    CustomButton restartGame;
    CustomButton Exit;
    CustomButton MainMenuB;
    Text gameOver;

    public GameOverScene(MainMenu mainMenu) {
        restartGame = new CustomButton("Restart");
        restartGame.setOnAction(event1 -> {
            window.close();
            new LevelOne().show(mainMenu);
        });
        Exit = new CustomButton("Exit");
        Exit.setOnAction(event -> {
            window.close();
        });

        MainMenuB=new CustomButton("Main Menu");
        MainMenuB.setOnAction(event -> {
            window.close();
            mainMenu.window.show();
        });


        gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameOver.setCache(true);
        gameOver.setFill(Color.PALEVIOLETRED);
        gameOver.setTranslateY(gameOver.getTranslateY() - 10);


    }

    public void show() {
        window = new Stage();

        layout = new VBox(20, gameOver, restartGame,MainMenuB, Exit);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #000000;");
        scene = new Scene(layout, 800, 600);


        window.setScene(scene);
        window.show();


    }

    public void updateDatabase(ScoreBoard sb) {
        //send queries from the data of the given scoreboard object.
    }


}
