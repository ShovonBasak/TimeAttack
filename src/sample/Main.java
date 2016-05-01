package sample;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import scenes.LevelOne;
import scenes.LoadingScreen;
import scenes.MainMenu;

public class Main extends Application{
    LevelOne levelOne;

    public void start(Stage primaryStage) throws Exception{
        //LoadingScreen l = new LoadingScreen();
        //l.show();
        MainMenu m = new MainMenu();
        m.show();
        //levelOne = new LevelOne();
        //levelOne.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
