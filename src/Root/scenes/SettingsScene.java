package Root.scenes;


import Root.Application.AudioManager;
import Root.Application.Main;
import Root.UserInterface.CustomButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class SettingsScene {
   private BorderPane layout;
    private Scene scene;
    private  Slider slider;

    public SettingsScene(Main mainMenu) {
        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        Text settingsText=new Text("Settings");
        settingsText.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        settingsText.setCache(true);
        settingsText.setFill(Color.web("#05FFB8"));
        settingsText.setTranslateX(settingsText.getLayoutX()+300);

        top.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        CustomButton backButton=new CustomButton("Back");
        backButton.setOnAction(event ->{
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
        } );
        top.getChildren().addAll(backButton,settingsText);
        layout.setTop(top);

        VBox left=new VBox(30);
        left.setAlignment(Pos.CENTER);
        left.setMinWidth(75);
        left.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        layout.setLeft(left);

        VBox right=new VBox();
        right.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        right.setFillWidth(true);
        right.setMinWidth(75);
        layout.setRight(right);

        VBox bot=new VBox();
        bot.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        bot.setFillWidth(true);
        bot.setMinHeight(40);
        layout.setBottom(bot);

        //swappable middleLayout

        //audio by default
        AudioSettings();






        scene=new Scene(layout,800,600);
    }



    public Scene getScene() {
        return  scene;
    }

    private void AudioSettings(){
        //Controls Scene
        VBox ObjectiveScene=new VBox(20);
        ObjectiveScene.setAlignment(Pos.TOP_CENTER);
        ObjectiveScene.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitleC=new Text("Audio");
        groupTitleC.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));
        groupTitleC.setCache(true);
        groupTitleC.setFill(Color.BEIGE);


        //slider setup
        HBox maxVolume= new HBox(10);
        maxVolume.setAlignment(Pos.TOP_LEFT);
        Text maxVolText=new Text("Volume");
        maxVolText.setFont(Font.font("Lucida Calligraphy", FontWeight.SEMI_BOLD, 20));
        maxVolText.setCache(true);
        maxVolText.setFill(Color.FUCHSIA);


        slider = new Slider();
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(AudioManager.volume);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(.1);

        slider.valueProperty().addListener((ov, old_val, new_val) -> {
            AudioManager.volume=slider.getValue();
            AudioManager.updateVolume();
        });


        maxVolume.getChildren().addAll(maxVolText,slider);



        ObjectiveScene.getChildren().addAll(groupTitleC,maxVolume);
        layout.setCenter(ObjectiveScene);
    }


}
