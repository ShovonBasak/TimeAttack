package scenes;


import Application.Main;
import Application.ScoreBoard;
import UserInterface.CustomButton;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class instructionsScene {
    Scene scene;
    VBox layout;
    Text text;
    CustomButton backButton;

    public instructionsScene(Main mainMenu) {

        text = new Text("INSTRUCTIONS");
        text.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        text.setCache(true);
        text.setFill(Color.YELLOWGREEN);
        text.setTranslateX(text.getTranslateX() + 230);


        layout = new VBox(10);
        layout.setStyle("-fx-background-color: #C7A0A0;");

        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));


        setScene();
    }

    public void setScene() {
        HBox hBox = new HBox(backButton, text);
        hBox.setStyle("-fx-background-color: #276EB1;");
        layout.getChildren().addAll(hBox);
        scene = new Scene(layout, 800, 600);

    }

    public Scene getScene() {
        return this.scene;
    }

}
