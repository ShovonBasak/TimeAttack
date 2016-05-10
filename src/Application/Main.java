package application;

import userInterface.CustomButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scenes.HighScoreScene;
import scenes.GameScene;
import scenes.LoadingScreen;
import scenes.InstructionsScene;


public class Main{
    //Class variables

    public Stage window;
    public Scene scene;
    public VBox layout;
    public CustomButton startGame;
    public CustomButton exit;
    public CustomButton highScore;
    public CustomButton instructions;
    public Text gameName;

    //scenes



    public Stage getWindow() {
        return window;
    }

    public Scene getScene() {
        return scene;
    }

    public Main() {


        //initialize buttons
        startGame = new CustomButton("Start Game");
        startGame.setOnAction(event1 -> {
            window.setScene(new GameScene(this).getScene());
            window.setFullScreen(true);
        });
        exit = new CustomButton("Exit");
        exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        instructions = new CustomButton("Instructions");
        instructions.setOnAction(event1 -> {
            window.setScene(new InstructionsScene(this).getScene());
        });


        highScore = new CustomButton("High Score");
        highScore.setOnAction(event -> {

            window.setScene(new HighScoreScene(this).getScene());

        });

        gameName = new Text("Time Attack");
        gameName.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        gameName.setCache(true);
        gameName.setFill(Color.MAROON);
        gameName.setTranslateY(gameName.getTranslateY() - 20);
    }

}
