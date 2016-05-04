package scenes;


import Application.Main;
import UserInterface.CustomButton;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class instructionsScene {
    Scene scene;
    BorderPane layout;
    Text text;
    CustomButton backButton;

    public instructionsScene(Main mainMenu) {

        layout = new BorderPane();

        text = new Text("INSTRUCTIONS");
        text.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 25));
        text.setCache(true);
        text.setFill(Color.YELLOWGREEN);
        text.setTranslateX(text.getTranslateX() + 230);


        layout.setStyle("-fx-background-color: #4d7674;");



        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));


        setScene();
    }

    public void setScene() {
        HBox hBox = new HBox(backButton, text);
        hBox.setStyle("-fx-background-color: #276EB1;");
        layout.setTop(hBox);


        scene = new Scene(layout, 800, 600);

    }

    public Scene getScene() {
        return this.scene;
    }

}
