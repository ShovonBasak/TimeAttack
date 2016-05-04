package scenes;


import Application.Main;
import UserInterface.CustomButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class instructionsScene {
    Scene scene;
    BorderPane layout;
    Text text;
    CustomButton backButton;
    Text centerText;

    public instructionsScene(Main mainMenu) {

        layout = new BorderPane();

        centerText = new Text("Objective:" +
                "\nCollect coin's without touching enemies." +
                "\nControls:\n" +
                "Use mouse movement to control the player." +
                "\nEnemies:\n" +
                "Blue will follow you and Red is like a bounce ball.");


        centerText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 30));
        centerText.setFill(Color.WHEAT);



        text = new Text("INSTRUCTIONS");
        text.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 25));
        text.setCache(true);
        text.setFill(Color.ALICEBLUE);
        text.setTranslateX(text.getTranslateX() + 230);


        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));


        setScene();
    }

    public void setScene() {
        HBox top = new HBox(backButton, text);
        top.setStyle("-fx-background-color: #33001a;");
        top.setPrefSize(50, 50);
        layout.setTop(top);

        VBox left = new VBox();
        left.setStyle("-fx-background-color: #33001a;");
        left.setPrefSize(100, 100);
        layout.setLeft(left);

        VBox right = new VBox();
        right.setStyle("-fx-background-color: #33001a;");
        right.setPrefSize(100, 100);
        layout.setRight(right);

        VBox bot = new VBox();
        bot.setStyle("-fx-background-color: #33001a;");
        bot.setPrefSize(50, 50);
        layout.setBottom(bot);

        VBox center = new VBox(centerText);
        center.setAlignment(Pos.CENTER);
        center.setStyle("-fx-background-color: #660033;");

        layout.setCenter(center);




        scene = new Scene(layout, 800, 600);

    }

    public Scene getScene() {
        return this.scene;
    }

}
