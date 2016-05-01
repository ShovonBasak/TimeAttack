package Application;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.GameOverScene;
import scenes.LevelOne;

public class Main extends Application{
    LevelOne levelOne;

    public void start(Stage primaryStage) throws Exception{
        //LoadingScreen l = new LoadingScreen();
        //l.show();
        //MainMenu m = new MainMenu();
        //m.show();
        levelOne = new LevelOne();
        levelOne.show();
        //GameOverScene g = new GameOverScene();
        // g.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
