package scenes;


import Application.ScoreBoard;
import UserInterface.CustomButton;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class highScoreScene {
    Stage window;
    Scene scene;
    VBox layout;
    CustomButton backButton;
    TableView<ScoreBoard> highScoreBoard;

    public highScoreScene(MainMenu mainMenu) {
        window = new Stage();
        layout = new VBox(10);
        layout.setStyle("-fx-background-color: #4d004d;");
        backButton = new CustomButton("Back");
        backButton.setOnAction(event -> {
            window.close();
            mainMenu.window.show();
        });


        highScoreBoard = new TableView<>();
        highScoreBoard.setStyle("-fx-background-color: #4d004d;");
        highScoreBoard.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setTable();

    }

    public void show() {

        layout.getChildren().addAll(backButton, highScoreBoard);
        scene = new Scene(layout, 800, 600);
        window.setScene(scene);


        window.show();
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
        highScoreBoard.getItems().addAll(new ScoreBoard("1", "1", "1"));
    }


}