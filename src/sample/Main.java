package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.LevelOne;

public class Main extends Application{
    LevelOne levelOne;

    public void start(Stage primaryStage) throws Exception{
        levelOne = new LevelOne();
        levelOne.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
