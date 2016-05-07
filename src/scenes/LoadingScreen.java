package scenes;


import application.Main;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoadingScreen {
    public Scene scene;
    Text companyName;
    Text presents;
    public Text gameName;

    public LoadingScreen(Main mainMenu) {

        companyName = new Text("২ টাকার Developer");
        companyName.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        companyName.setCache(true);
        companyName.setFill(Color.RED);

        presents = new Text("Presents");
        presents.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        presents.setCache(true);
        presents.setFill(Color.GREEN);
        presents.setTranslateX(presents.getTranslateX() + 200);


        gameName = new Text("TimeAttack");
        gameName.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        gameName.setCache(true);
        gameName.setFill(Color.BLUE);

        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        gameName.setEffect(reflection);

        setScene(mainMenu);

    }

    public void setScene(Main mainMenu) {



        VBox layout = new VBox(20, companyName, presents, gameName);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #000000;");
        scene = new Scene(layout, 800, 600);


        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            mainMenu.getWindow().setScene(mainMenu.getScene());
            //mainMenu.getWindow().setFullScreen(true);
            //mainMenu.getWindow().setFullScreenExitHint("");
        });

        scene.addEventFilter(KeyEvent.ANY, event -> {
            mainMenu.getWindow().setScene(mainMenu.getScene());
        });



        FadeTransition ft = new FadeTransition(Duration.millis(5000), companyName);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.play();

        ft = new FadeTransition(Duration.millis(10000), presents);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.play();


        ft = new FadeTransition(Duration.millis(20000), gameName);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.play();



    }

    public Scene getScene() {

        return this.scene;
    }


}
