/**
How this works?
 This is The main Menu.It handles other game Root.scenes.Other Root.scenes transit into and out of this.
 Main Function creates an instance of main menu and it's components.
 Whenever scene transition happens this object is passed so that this scene can be called back.
 */

package Root.Application;

import Root.UserInterface.CustomButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Root.scenes.GameScene;
import Root.scenes.HighScoreScene;
import Root.scenes.helpScene;
import Root.scenes.LoadingScreen;

import java.util.Optional;



public class Main extends Application {
    //Class variables

    private Stage window;
    private Scene scene;
    private VBox layout;
    private CustomButton startGame;
    private CustomButton exit;
    private CustomButton highScore;
    private CustomButton helpButton;
    private Text gameName;
    private MediaPlayer mediaPlayer;


    //Root.scenes


    public Stage getWindow() {
        return window;
    }

    public Scene getScene() {
        return scene;
    }

    public Main() {
        //initialize buttons

        startGame = new CustomButton("Start Game");
        startGame.setOnAction(event1 -> {
            buttonAudio();
            mediaPlayer.stop();
            window.setScene(new GameScene(this).getScene());
            window.setFullScreen(true);
        });
        exit = new CustomButton("Exit");
        exit.setOnAction(event -> {
            buttonAudio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT!");
            alert.setHeaderText("Are you sure?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
                System.exit(0);
            }

        });

        helpButton = new CustomButton("Help");
        helpButton.setOnAction(event1 -> {
            buttonAudio();
            window.setScene(new helpScene(this).getScene());
        });


        highScore = new CustomButton("High Score");
        highScore.setOnAction(event ->{
            buttonAudio();
            window.setScene(new HighScoreScene(this).getScene());
        } );

        gameName = new Text("Time Attack");
        gameName.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        gameName.setCache(true);
        gameName.setFill(Color.MAROON);
        gameName.setTranslateY(gameName.getTranslateY() - 20);
    }
    public void start(Stage window) throws Exception {
        this.window = window;
        window.setScene(new LoadingScreen(this).getScene());



        //setup MainMenu
        layout = new VBox(20, gameName, startGame, highScore, helpButton, exit);

        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(#368fb4, #7ad3f8);");
        scene = new Scene(layout, 800, 600);

        window.setTitle("Time Attack");

        window.setResizable(false);
        window.show();

        window.setOnCloseRequest(event -> {
                    event.consume();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("ALERT!");
                    alert.setHeaderText("Are you sure?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Platform.exit();
                        System.exit(0);
                    }


                }
        );
    }
    private void buttonAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//Button01.wav");

            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void backgroundAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//MainMenu.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
