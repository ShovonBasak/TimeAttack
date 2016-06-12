package Root.scenes;


import Root.UserInterface.CustomButton;
import Root.Application.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class helpScene {
   private BorderPane layout;
    private Scene scene;
    private CustomButton controlButton=new CustomButton("Controls");

    private CustomButton enemyButton=new CustomButton("Enemies");
    private CustomButton objectiveButton=new CustomButton("Objective");

    public helpScene(Main mainMenu) {
        layout=new BorderPane();

        HBox top=new HBox();
        top.setMinHeight(40);
        top.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        CustomButton backButton=new CustomButton("Back");
        backButton.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));
        top.getChildren().addAll(backButton);
        layout.setTop(top);

        VBox left=new VBox(30);
        left.setAlignment(Pos.CENTER);
        left.getChildren().addAll(controlButton,enemyButton,objectiveButton);
        left.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        layout.setLeft(left);

        VBox right=new VBox();
        right.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        right.setFillWidth(true);
        right.setMinWidth(75);
        layout.setRight(right);

        VBox bot=new VBox();
        bot.setStyle("-fx-background-color: linear-gradient(#780bde, #062f42);");
        bot.setFillWidth(true);
        bot.setMinHeight(40);
        layout.setBottom(bot);

        //swappable middleLayout


        //set ButtonAction
        enemyButton.setOnAction(e->setEnemyScene());

        controlButton.setOnAction(e->setControlScene());

        objectiveButton.setOnAction(e->setObjectiveScene());











        //defaultbuttonScene
         setEnemyScene();




        scene=new Scene(layout,800,600);
    }



    public Scene getScene() {
        return  scene;
    }

    private void setEnemyScene(){
        //enemyButton scene
        VBox enemyGroup=new VBox();
        enemyGroup.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitle=new Text("Enemies");
        groupTitle.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        groupTitle.setCache(true);
        groupTitle.setFill(Color.web("#05FFB8"));
        groupTitle.setTranslateX(groupTitle.getLayoutX()+275);

        //enemy1info
        ImageView enemy1Image=new ImageView(new Image("Root/Resources/image/enemy1.gif"));
        Text enemy1Text=new Text("They will bounce off walls and the coins even among other enemies.");
        enemy1Text.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        enemy1Text.setFill(Color.web("#FF054C"));
        enemy1Text.setTranslateX(enemy1Text.getLayoutX()+60);
        enemy1Text.setTranslateY(enemy1Text.getLayoutY()-20);
        //enemy2Info
        ImageView enemy2Image=new ImageView(new Image("Root/Resources/image/enemy2.gif"));
        enemy2Image.setFitHeight(50);
        enemy2Image.setFitWidth(50);
        Text enemy2Text=new Text("Don't Fall for it's cute outlook. They will follow you to your death.");
        enemy2Text.setFont(Font.font("Harrington", FontWeight.EXTRA_BOLD, 19));
        enemy2Text.setFill(Color.web("#FF054C"));
        enemy2Text.setTranslateX(enemy1Text.getLayoutX()+60);
        enemy2Text.setTranslateY(enemy1Text.getLayoutY()-20);

        //setupEnemiesLayout
        enemyGroup.getChildren().addAll(groupTitle,enemy1Image,enemy1Text,enemy2Image,enemy2Text);
        layout.setCenter(enemyGroup);
    }

    private void setControlScene(){
        //Controls Scene
        VBox conrtolsMenu=new VBox();
        conrtolsMenu.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitleC=new Text("Controls");
        groupTitleC.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        groupTitleC.setCache(true);
        groupTitleC.setFill(Color.web("#05FFB8"));
        groupTitleC.setTranslateX(conrtolsMenu.getLayoutX()+275);
        conrtolsMenu.getChildren().addAll(groupTitleC);
        layout.setCenter(conrtolsMenu);
    }


    private void setObjectiveScene(){
        //Controls Scene
        VBox ObjectiveScene=new VBox();
        ObjectiveScene.setAlignment(Pos.TOP_CENTER);
        ObjectiveScene.setStyle("-fx-background-color: linear-gradient(#020300, #14b897);");
        Text groupTitleC=new Text("Objective");
        groupTitleC.setFont(Font.font("Chiller", FontWeight.BOLD, 40));
        groupTitleC.setCache(true);
        groupTitleC.setFill(Color.web("#05FFB8"));

        Text objectiveText=new Text("Collect coins and evade the enemies.\n" +
                "Collect the coins before timer runs out.");
        objectiveText.setFill(Color.web("#99ccff"));
        objectiveText.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, 40));




        ObjectiveScene.getChildren().addAll(groupTitleC,objectiveText);
        layout.setCenter(ObjectiveScene);
    }


}
