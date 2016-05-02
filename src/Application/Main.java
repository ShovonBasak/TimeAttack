package Application;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.LevelOne;
import scenes.LoadingScreen;
import scenes.MainMenu;

public class Main extends Application{
    MainMenu mainMenu = new MainMenu();

    public void start(Stage primaryStage) throws Exception{
        LoadingScreen l = new LoadingScreen();
        l.show(mainMenu);

        //GameOverScene g = new GameOverScene();
        // g.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
