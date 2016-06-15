package Root.scenes;


import Root.Application.AudioManager;
import Root.Application.Main;
import Root.UserInterface.ScoreBoard;
import Root.UserInterface.CustomButton;
import Root.gameData.XMLService;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Optional;


public class HighScoreScene {

    private Scene scene;
    private VBox layout;
    private Text text;
    private CustomButton backButton;
    private CustomButton clearButton;
    private TableView<ScoreBoard> highScoreBoard;
    private Main mainMenu;
    private  XMLService x;
    public HighScoreScene(Main mainMenu) {
        this.mainMenu=mainMenu;
        try{x= new XMLService();}
        catch (Exception e){
            //Failed to get Data
        }
        highScoreBoard = new TableView<>();
        setTable();

        text = new Text("High Score");
        text.setFont(Font.font("Old English Text MT", FontWeight.BOLD, 30));
        text.setCache(true);
        text.setFill(Color.YELLOWGREEN);
        text.setTranslateX(300);

        layout = new VBox();
        layout.setStyle("-fx-background-color: #2F4F4F;");

        //button with action to return to scene
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            mainMenu.getWindow().setScene(mainMenu.getScene());
        });
        clearButton= new CustomButton("Clear");
        clearButton.setTranslateX(545);
        clearButton.setOnAction(event -> {
            AudioManager.buttonAudio();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING!");
            alert.setHeaderText("Are you sure you wish to clear the Table?");
            alert.setContentText("You can't Undo this process");


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                x.clearScoreBoard();
                mainMenu.getWindow().setScene(new HighScoreScene(mainMenu).getScene());
            }

        });


    }

    private void setScene() {
        HBox hBox = new HBox(backButton, text,clearButton);
        hBox.setStyle("-fx-background-color: #2F4F4F;");


        layout.getChildren().addAll(hBox, highScoreBoard);

        scene = new Scene(layout, 800, 600);
    }

    public Scene getScene() {
        updateTable();
        setScene();
        return this.scene;
    }


    private void setTable() {
        //Takes an scoreBoardObject
        //columns
        //title

        highScoreBoard.setStyle("-fx-background-color: linear-gradient(#e2ecfe, #99bcfd);" +
                "-fx-background-color: linear-gradient(from 0% 0% to 50% 50%,#3278fa,#99bcfd);" +
                "-fx-border-width: 2px;" +
                "-fx-alignment:CENTER;");
        highScoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        TableColumn<ScoreBoard, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-alignment:CENTER;");


        //Value
        TableColumn<ScoreBoard, ScoreBoard> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setStyle("-fx-alignment:CENTER;");


        //comment
        TableColumn<ScoreBoard, String> levelColumn = new TableColumn<>("Max Level Reached");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("lvlReached"));
        levelColumn.setStyle("-fx-alignment:CENTER;");


        //addColumns on table
        highScoreBoard.getColumns().add(nameColumn);
        highScoreBoard.getColumns().add(scoreColumn);
        highScoreBoard.getColumns().add(levelColumn);


        highScoreBoard.setMinHeight(mainMenu.getWindow().getHeight());

    }



    private void updateTable() {
        //fetch data from Root.database and get and array and update like this code from a loop.
        //1 row = 1 scoreboard object get and arraylist from the Root.database and create objects from those.
        try {
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
