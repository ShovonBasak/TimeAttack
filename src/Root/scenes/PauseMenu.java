package Root.scenes;

import Root.Application.AudioManager;
import Root.Application.Main;
import Root.UserInterface.CustomButton;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Optional;


class PauseMenu {
    private Main mainMenu;
    private GameScene gameScene;
    private CustomButton resumeButton;
    private CustomButton restartButton;
    private CustomButton settingsButton;
    private CustomButton quitButton;
    private Scene scene;

    PauseMenu(Main mainMenu, GameScene gameScene){
        setScene();
        this.mainMenu=mainMenu;
        this.gameScene=gameScene;
        AudioManager.mediaPlayer.stop();



    }

    private void setScene(){
        VBox layout;
        Text MenuText=new Text("Pause Menu");
        MenuText.setFont(Font.font("Blackadder ITC", FontWeight.BOLD, 60));
        MenuText.setCache(true);
        MenuText.setFill(Color.MAROON);
        MenuText.setTranslateY(MenuText.getTranslateY() - 20);


        resumeButton=new CustomButton("Resume");
        resumeButton.setOnAction(event -> {
            gameScene.resume();
            mainMenu.getWindow().setScene(gameScene.getScene());
            mainMenu.getWindow().setFullScreen(true);
            AudioManager.mediaPlayer.play();
        });

        restartButton=new CustomButton("Restart");
        restartButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Restart Game?");
            alert.setContentText("All progress will be lost");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                GameScene.isPaused=false;

                mainMenu.getWindow().setScene(new GameScene(mainMenu).getScene());
                mainMenu.getWindow().setFullScreen(true);
            }

        });
        settingsButton=new CustomButton("Settings");
        settingsButton.setOnAction(event -> {
            mainMenu.getWindow().setScene(new SettingsScene(mainMenu,this).getScene());
            AudioManager.mediaPlayer.play();
        });




        quitButton=new CustomButton("Quit");
        quitButton.setOnAction(e->{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Quit Game?");
            alert.setContentText("This will take you back to main Menu");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                mainMenu.getWindow().setScene(mainMenu.getScene());
                AudioManager.MainMenuAudio();
            }

        });



        layout = new VBox(MenuText);
        layout.getChildren().addAll(resumeButton,restartButton,settingsButton,quitButton);
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient(#368fb4, #7ad3f8);");
        scene = new Scene(layout, 800, 600);




    }

    public Scene getScene(){
        return this.scene;
    }



}
