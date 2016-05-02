package scenes;


import Application.ScoreBoard;
import UserInterface.CustomButton;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class instructionScene {
    Stage window;
    Scene scene;
    VBox layout;
    CustomButton backButton;


    public instructionScene(MainMenu mainMenu) {
        window = new Stage();
        layout = new VBox(10);
        layout.setStyle("-fx-background-color: #4d004d;");
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> {
            window.close();
            mainMenu.window.show();
        });


    }

    public void show() {

        layout.getChildren().addAll(backButton);
        scene = new Scene(layout, 800, 600);
        window.setScene(scene);


        window.show();
    }
}
