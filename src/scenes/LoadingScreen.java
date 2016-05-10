package scenes;


import application.Main;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView loadingScreen;

    public LoadingScreen(Main mainMenu) {
        Image image= new Image("Resources/image/loadingScreen.jpg");
        loadingScreen= new ImageView(image);
        loadingScreen.setFitWidth(800);
        loadingScreen.setFitHeight(600);
        loadingScreen.setPreserveRatio(true);
        loadingScreen.setSmooth(true);
        loadingScreen.setCache(true);


        setScene(mainMenu);

    }

    public void setScene(Main mainMenu) {



        VBox layout = new VBox(20,loadingScreen);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #000000;");
        scene = new Scene(layout, 800, 600);


        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            mainMenu.getWindow().setScene(mainMenu.getScene());
        });

        scene.addEventFilter(KeyEvent.ANY, event -> {
            mainMenu.getWindow().setScene(mainMenu.getScene());
        });



        FadeTransition ft = new FadeTransition(Duration.millis(10000), loadingScreen);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.play();





    }

    public Scene getScene() {

        return this.scene;
    }


}
