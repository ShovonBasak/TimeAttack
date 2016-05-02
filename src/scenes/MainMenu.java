package scenes;


import UserInterface.CustomButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainMenu {
    Stage window;
    public Scene scene;
    VBox layout;
    CustomButton StartGame;
    CustomButton Exit;
    CustomButton HighScore;
    Text gameName;

    public MainMenu() {
        StartGame = new CustomButton("Start Game");
        StartGame.setOnAction(event1 -> {
            //do something
        });
        Exit = new CustomButton("Exit");
        Exit.setOnAction(event -> {
            //do something
        });
        HighScore = new CustomButton("High Score");
        HighScore.setOnAction(event -> {
            window.close();
            highScoreScene s = new highScoreScene(this);
            s.show();
        });

        gameName = new Text("Time Attack");
        gameName.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameName.setCache(true);
        gameName.setFill(Color.DARKBLUE);
        gameName.setTranslateY(gameName.getTranslateY() - 10);


    }

    public void show() {
        window = new Stage();

        layout = new VBox(20, gameName, StartGame, HighScore, Exit);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #663300;");
        scene = new Scene(layout, 800, 600);


        window.setScene(scene);
        window.show();


    }





}
