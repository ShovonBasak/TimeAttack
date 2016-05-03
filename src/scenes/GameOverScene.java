package scenes;


import Application.Main;
import Application.ScoreBoard;
import UserInterface.CustomButton;
import UserInterface.ScoreLabel;
import database.DBService;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class GameOverScene {
    public Scene scene;
    VBox layout;
    CustomButton Next;
    ScoreLabel scoreLabel;
    TextField NameField;


    Text gameOver;
    Text Score;
    Text nameLable;

    public GameOverScene(Main mainMenu, ScoreLabel scoreLabel) {
        this.scoreLabel = scoreLabel;

        Next = new CustomButton("Next");
        Next.setOnAction(event1 -> {

            String name = NameField.getText();
            ScoreBoard SB = new ScoreBoard(name, String.valueOf(scoreLabel.getScore()), "1");
            updateDatabase(SB);
            mainMenu.getWindow().setScene(mainMenu.getHighScoreScene());
        });

        NameField = new TextField();
        NameField.setMaxWidth(200);

        nameLable = new Text("ENTER YOUR NAME");
        nameLable.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        nameLable.setCache(true);
        nameLable.setFill(Color.AZURE);


        gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        gameOver.setCache(true);
        gameOver.setFill(Color.RED);
        gameOver.setTranslateY(gameOver.getTranslateY() - 20);


        Score = new Text("Score:" + String.valueOf(scoreLabel.getScore()));
        Score.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        Score.setCache(true);
        Score.setFill(Color.AQUA);


        setScene();

    }

    public void setScene() {
        layout = new VBox(20, gameOver, Score, nameLable, NameField, Next);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600);
        layout.setStyle("-fx-background-color: #000000;");
    }

    public Scene getScene() {
        return this.scene;
    }


    public void updateDatabase(ScoreBoard sb) {
        //send queries from the data of the given scoreboard object.
        DBService ds = new DBService();
        ds.updateScoreBoard(sb.getName(), sb.getScore(), sb.getLvlReached());
    }


}
