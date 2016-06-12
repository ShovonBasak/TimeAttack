package Root.scenes;

import Root.Application.Main;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

class TitleScreen {
    public Scene scene;
    private ImageView titleScreen;
    private Main mainMenu;
    private Text prompt;
    private MediaPlayer mediaPlayer;

    private Image titleImage= new Image("Root/Resources/image/Time Attack.jpg");

    TitleScreen(Main mainMenu) {
        backgroundAudio();
        prompt= new Text("Press Any Key");
        prompt.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        prompt.setCache(true);
        prompt.setFill(Color.CORAL);
        prompt.setTranslateY(prompt.getTranslateY() - 50);

        this.mainMenu = mainMenu;

        setScene(mainMenu);

    }

    public void setScene(Main mainMenu) {
        BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(titleImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.ROUND, BackgroundPosition.CENTER,backgroundSize);
        Background background = new Background(backgroundImage);

        VBox layout = new VBox(20,prompt);
        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setBackground(background);
       // layout.setStyle( "-fx-background-repeat: stretch;");
        scene = new Scene(layout, 800, 600);


        FadeTransition ft = new FadeTransition(Duration.millis(500), prompt);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);

        ft.play();



        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            mediaPlayer.stop();
            keyPressAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
            mainMenu.backgroundAudio();
            ft.stop();
        });

        scene.addEventFilter(KeyEvent.ANY, event -> {
            mediaPlayer.stop();
            keyPressAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
            mainMenu.backgroundAudio();
            ft.stop();
        });
    }

    public Scene getScene() {

        return this.scene;
    }

    private void backgroundAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//src//Root//Resources//AudioClip//TitleScreenAudio.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void keyPressAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//src//Root//Resources//AudioClip//TitleClickSound.mp3");

            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
