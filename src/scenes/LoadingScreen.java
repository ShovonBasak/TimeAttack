package scenes;


import Application.Main;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sun.awt.SunToolkit;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class LoadingScreen {
    public Scene scene;
    private ImageView loadingScreen;
    private Main mainMenu;
    private MediaPlayer mediaPlayer;
    public LoadingScreen(Main mainMenu) {


        this.mainMenu=mainMenu;
        Image loadingImage = new Image("Resources/image/loadingScreen.jpg");
        loadingScreen= new ImageView(loadingImage);
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


        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
            mainMenu.getWindow().setScene(new TitleScreen(mainMenu).getScene());
            mediaPlayer.stop();
        });

        scene.addEventFilter(KeyEvent.ANY, event -> {
            mainMenu.getWindow().setScene(new TitleScreen(mainMenu).getScene());
            mediaPlayer.stop();
        });



        FadeTransition ft = new FadeTransition(Duration.millis(10000), loadingScreen);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);

        ft.play();
        Media audioClip = new Media("file:///" +
                System.getProperty("user.dir").replace("\\","//")+
                "//src//Resources//AudioClip//LoadingScreen.mp3");

        mediaPlayer= new MediaPlayer(audioClip);
        mediaPlayer.play();




    }

    public Scene getScene() {

        return this.scene;
    }


}
