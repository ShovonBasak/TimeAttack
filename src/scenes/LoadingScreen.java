package scenes;


import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.effect.Reflection;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingScreen {
    public Stage window;
    public Scene scene;
    Text companyName;
    Text presents;
    public Text gameName;

    public LoadingScreen() {

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

    }

    public void show() {

        window = new Stage();

        VBox layout = new VBox(20, companyName, presents, gameName);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600, Color.valueOf("Black"));
        window.setScene(scene);
        window.show();

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


}
