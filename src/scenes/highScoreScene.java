package scenes;


import Application.Main;
import Application.ScoreBoard;
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
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;


public class highScoreScene {

    Scene scene;
    VBox layout;
    Text text;
    CustomButton backButton;
    TableView<ScoreBoard> highScoreBoard;

    public highScoreScene(Main mainMenu) {

        text = new Text("High Score");
        text.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        text.setCache(true);
        text.setFill(Color.YELLOWGREEN);
        text.setTranslateX(text.getTranslateX() + 250);

        layout = new VBox(10);
        layout.setStyle("-fx-background-color: #276EB1;");

        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> mainMenu.getWindow().setScene(mainMenu.getScene()));


        highScoreBoard = new TableView<>();
        highScoreBoard.setStyle("-fx-background-color: #27B1AF;");
        highScoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setTable();
        setScene();

    }

    public void setScene() {
        HBox hBox = new HBox(backButton, text);
        hBox.setStyle("-fx-background-color: #276EB1;");

        layout.getChildren().addAll(hBox, highScoreBoard);
        scene = new Scene(layout, 800, 600);

    }

    public Scene getScene() {
        updateTable();
        return this.scene;
    }


    public void setTable() {
        //Takes an scoreBoardObject
        //columns
        //title
        TableColumn<ScoreBoard, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Value
        TableColumn<ScoreBoard, ScoreBoard> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        //comment
        TableColumn<ScoreBoard, String> levelColumn = new TableColumn<>("Max Level Reached");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("lvlReached"));

        //addColumns on table
        highScoreBoard.getColumns().add(nameColumn);
        highScoreBoard.getColumns().add(scoreColumn);
        highScoreBoard.getColumns().add(levelColumn);

    }



    public void updateTable() {
        //fetch data from database and get and array and update like this code from a loop.
        //1 row = 1 scoreboard object get and arraylist from the database and create objects from those.
        try {
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
