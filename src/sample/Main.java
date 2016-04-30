package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.GameScene;

public class Main extends Application{
    GameScene gameScene;

    public void start(Stage primaryStage) throws Exception{
        gameScene = new GameScene();
        gameScene.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
