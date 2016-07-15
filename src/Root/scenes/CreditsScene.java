package Root.scenes;

import Root.Application.AudioManager;
import Root.Application.Main;
import Root.GameObjects.PickUps.ObjectTimer;
import Root.GameObjects.Player;
import Root.UserInterface.CustomButton;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static javafx.scene.input.KeyCode.ESCAPE;

public class CreditsScene implements Runnable{
    Scene scene;
    BorderPane layout;
    List<Text> credits;
    ObjectTimer timer;
    Thread thisThread;
    Text showCredit;
    public CreditsScene(Main mainMenu){
        timer=new ObjectTimer();
        credits= new ArrayList<>();
        setupCredit();
        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        Text settingsText=new Text("Credits");
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

        //Text part
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color:#000000");


                showCredit = credits.get(0);
                vbox.getChildren().add(showCredit);

                //FadeINout
                FadeTransition ft = new FadeTransition(Duration.millis(3000), showCredit);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.setCycleCount(Animation.INDEFINITE);
                ft.setAutoReverse(true);

                ft.play();







        layout.setCenter(vbox);
        scene = new Scene(layout, 800,600);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                mainMenu.getWindow().setScene(mainMenu.getScene());
            }
        });

        thisThread=new Thread(this);
        thisThread.start();

    }

    public Scene getScene(){
        return this.scene;
    }

    public void setupCredit(){
        Text text=new Text();
        text.setFill(Color.LIMEGREEN);
        text.setFont(Font.font("Wide Latin", FontWeight.BOLD, 30));

        text.setText("Project Manager\n" +
                "Khan,Tanimul Haque");
        credits.add(text);


        Text text2=new Text();
        text.setFill(Color.LIMEGREEN);
        text.setFont(Font.font("Wide Latin", FontWeight.BOLD, 30));

        text.setText("Programmer\n" +
                "Basak, Shovon");
        credits.add(text2);





    }


    @Override
    public void run() {
        while (!credits.isEmpty()) {
            Platform.runLater(() -> {
                int i=1;
                System.out.println(timer.getTime());
                if (timer.getTime() % 3 == 0) {
                    showCredit=credits.get(i++);
                }
            });
                try {
                    Thread.sleep(25);

                } catch (Exception e) {
            }
        }
    }
}
