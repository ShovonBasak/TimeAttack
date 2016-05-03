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
    public Scene scene;
    VBox layout;
    CustomButton restartGame;
    CustomButton Exit;
    CustomButton mainMenuB;
    Text gameOver;

    public GameOverScene(Main mainMenu) {
        restartGame = new CustomButton("Restart");
        restartGame.setOnAction(event1 -> {
            mainMenu.getWindow().setScene(new LevelOne(mainMenu).getScene());
        });


        mainMenuB = new CustomButton("Main Menu");
        mainMenuB.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));


        Exit = new CustomButton("Exit");
        Exit.setOnAction(event -> mainMenu.getWindow().close());


        gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameOver.setCache(true);
        gameOver.setFill(Color.PALEVIOLETRED);
        gameOver.setTranslateY(gameOver.getTranslateY() - 10);

        setScene();

    }

    public void setScene() {
        layout = new VBox(20, gameOver, restartGame, mainMenuB, Exit);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600);
        layout.setStyle("-fx-background-color: #000000;");
    }

    public Scene getScene() {
        return this.scene;
    }


    public void updateDatabase(ScoreBoard sb) {
        //send queries from the data of the given scoreboard object.
    }


}
