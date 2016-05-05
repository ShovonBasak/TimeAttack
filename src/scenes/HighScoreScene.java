package scenes;


import Application.Main;
import UserInterface.ScoreBoard;
import UserInterface.CustomButton;
import database.DBService;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class HighScoreScene {

    Scene scene;
    VBox layout;
    Text text;
    CustomButton backButton;
    TableView<ScoreBoard> highScoreBoard;
    Main mainMenu;

    public HighScoreScene(Main mainMenu) {
        this.mainMenu=mainMenu;
        text = new Text("High Score");
        text.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        text.setCache(true);
        text.setFill(Color.YELLOWGREEN);
        text.setTranslateX(mainMenu.getWindow().getWidth()/2-40);

        layout = new VBox();
        layout.setStyle("-fx-background-color: #2F4F4F;");

        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));

    }

    public void setScene() {
        HBox hBox = new HBox(backButton, text);
        hBox.setStyle("-fx-background-color: #2F4F4F;");


        layout.getChildren().addAll(hBox, highScoreBoard);

        scene = new Scene(layout, 800, 600);

    }

    public Scene getScene() {
        updateTable();
        setScene();
        return this.scene;
    }


    public void setTable() {
        //Takes an scoreBoardObject
        //columns
        //title
        highScoreBoard = new TableView<>();
        highScoreBoard.setStyle("-fx-background-color: linear-gradient(#e2ecfe, #99bcfd);" +
                "-fx-background-color: linear-gradient(from 0% 0% to 50% 50%,#3278fa,#99bcfd);" +
                "-fx-border-width: 2px;" +
                "-fx-alignment: CENTER;");
        highScoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        TableColumn<ScoreBoard, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-background-color: linear-gradient(#6725DE, #03BBC4);");

        //Value
        TableColumn<ScoreBoard, ScoreBoard> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setStyle("-fx-background-color: linear-gradient(#6725DE, #03BBC4);");

        //comment
        TableColumn<ScoreBoard, String> levelColumn = new TableColumn<>("Max Level Reached");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("lvlReached"));
        levelColumn.setStyle("-fx-background-color: linear-gradient(#6725DE, #03BBC4);");

        //addColumns on table
        highScoreBoard.getColumns().add(nameColumn);
        highScoreBoard.getColumns().add(scoreColumn);
        highScoreBoard.getColumns().add(levelColumn);


        highScoreBoard.setMinHeight(mainMenu.getWindow().getHeight());

    }



    public void updateTable() {
        //fetch data from database and get and array and update like this code from a loop.
        //1 row = 1 scoreboard object get and arraylist from the database and create objects from those.
        try {
            setTable();
            DBService x = new DBService();
            ArrayList<ScoreBoard> sb = x.getScoreList();

            for (ScoreBoard scoreBoard : sb) {
                highScoreBoard.getItems().addAll(scoreBoard);
            }
            highScoreBoard.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
