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


public class Main extends Application{
    //Class variables
    Stage window;
    Scene scene;
    VBox layout;
    CustomButton StartGame;
    CustomButton Exit;
    CustomButton HighScore;
    Text gameName;

    //scenes
    LoadingScreen loadingScreen;
    scenes.highScoreScene highScoreScene;

    //Level
    LevelOne levelOne;


    public Stage getWindow() {
        return window;
    }

    public Scene getScene() {
        return scene;
    }

    //constructor
    public Main() {
        //initialize scenes
        loadingScreen = new LoadingScreen(this);
        highScoreScene = new highScoreScene(this);

        //Levels
        //levelOne = new LevelOne(this);

        StartGame = new CustomButton("Start Game");
        StartGame.setOnAction(event1 -> {
            window.setScene(new LevelOne(this).getScene());

        });
        Exit = new CustomButton("Exit");
        Exit.setOnAction(event -> {
            Platform.exit();
            System.exit(0);

        });


        HighScore = new CustomButton("High Score");
        HighScore.setOnAction(event -> {
            window.setScene(highScoreScene.getScene());
        });

        gameName = new Text("Time Attack");
        gameName.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameName.setCache(true);
        gameName.setFill(Color.DARKBLUE);
        gameName.setTranslateY(gameName.getTranslateY() - 10);
    }


    public void start(Stage window) throws Exception {
        this.window = window;
        window.setScene(loadingScreen.getScene());
        //setup MainMenu
        layout = new VBox(20, gameName, StartGame, HighScore, Exit);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #663300;");
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
