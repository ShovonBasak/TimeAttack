package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.gameScene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        gameScene.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
