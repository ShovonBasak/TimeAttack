import application.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimeAttack extends Application{
    public Main main;

    public void start(Stage window) throws Exception {
        main = new Main();

        main.window = window;
        window.setScene(main.loadingScreen.getScene());

        //setup MainMenu
        main.layout = new VBox(20, main.gameName, main.startGame, main.highScore, main.instructions, main.exit);
        main.layout.setAlignment(Pos.CENTER);
        main.layout.setStyle("-fx-background-color: #B4EEB4;");
        main.scene = new Scene(main.layout, 800, 600);

        window.setTitle("Time Attack");

        window.setResizable(false);
        window.show();

        window.setOnCloseRequest(event -> {
                    Platform.exit();
                    System.exit(0);
                }
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
