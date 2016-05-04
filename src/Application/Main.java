package Application;

import UserInterface.CustomButton;
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
import scenes.highScoreScene;
import scenes.LevelOne;
import scenes.LoadingScreen;
import scenes.instructionsScene;


public class Main extends Application{
    //Class variables
    Stage window;
    Scene scene;
    VBox layout;
    CustomButton startGame;
    CustomButton exit;
    CustomButton highScore;
    CustomButton instructions;
    Text gameName;

    //scenes
    LoadingScreen loadingScreen;
    highScoreScene highScoreScene;
    instructionsScene instructionsScene;

    //Level
    LevelOne levelOne;


    public Stage getWindow() {
        return window;
    }

    public Scene getScene() {
        return scene;
    }

    public Scene getHighScoreScene() {
        return highScoreScene.getScene();
    }

    //constructor
    public Main() {
        //initialize scenes
        loadingScreen = new LoadingScreen(this);
        highScoreScene = new highScoreScene(this);

        //Levels


        //initialize buttons
        startGame = new CustomButton("Start Game");
        startGame.setOnAction(event1 -> {
            window.setScene(new LevelOne(this).getScene());

        });
        exit = new CustomButton("exit");
        exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);

        });

        instructions = new CustomButton("Instructions");
        instructions.setOnAction(event1 -> {
            instructionsScene = new instructionsScene(this);
            window.setScene(instructionsScene.getScene());
        });


        highScore = new CustomButton("High Score");
        highScore.setOnAction(event -> {
            window.setScene(highScoreScene.getScene());
        });

        gameName = new Text("Time Attack");
        gameName.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        gameName.setCache(true);
        gameName.setFill(Color.MAROON);
        gameName.setTranslateY(gameName.getTranslateY() - 20);
    }


    public void start(Stage window) throws Exception {
        this.window = window;
        window.setScene(loadingScreen.getScene());
        //setup MainMenu
        layout = new VBox(20, gameName, startGame, highScore, instructions, exit);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #B4EEB4;");
        scene = new Scene(layout, 800, 600);

        window.show();

        window.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(0);
                }
        );


    }


    public static void main(String[] args) {
        launch(args);
    }

}
