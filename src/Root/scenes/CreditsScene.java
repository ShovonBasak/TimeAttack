package Root.scenes;

import Root.Application.AudioManager;
import Root.Application.Main;
import Root.UserInterface.CustomButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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

import static javafx.scene.input.KeyCode.ESCAPE;

public class CreditsScene {
    Scene scene;
    BorderPane layout;

    public CreditsScene(Main mainMenu){

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


        VBox vbox = new VBox();
        for (int i = 0; i < 30; i++)
            vbox.getChildren().add(new Text(" longer line of text " + i + " "));

        //take a sideways picture to fit the cylinder
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setTransform(new Rotate(90));
        WritableImage snapshot = vbox.snapshot(snapshotParameters, null);

        //make sideways cyl with image
        PhongMaterial material = new PhongMaterial();


        Cylinder cylinder = new Cylinder(300, 650);
        cylinder.setStyle("-fx-background-color: linear-gradient(#1F03B5, #121716);");
        material.setDiffuseMap(snapshot);
        cylinder.setMaterial(material);
        cylinder.setRotate(-90);
        cylinder.setLayoutX(500);
        //lights camera show
        Group root = new Group();
        root.getChildren().add(cylinder);







        //I'll spin bob
        Rotate rx = new Rotate();
        rx.setAxis(Rotate.Y_AXIS);
        cylinder.getTransforms().add(rx);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
         KeyValue kv = new KeyValue(rx.angleProperty(), -360);
         KeyFrame kf = new KeyFrame(Duration.millis(10000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();



        layout.setCenter(root);
        scene = new Scene(layout, 800,600);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                mainMenu.getWindow().setScene(mainMenu.getScene());
            }
        });



    }

    public Scene getScene(){
        return this.scene;
    }

}
