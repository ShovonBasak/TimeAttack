package scenes;


import application.Main;
import userInterface.ScoreBoard;
import userInterface.CustomButton;
import userInterface.ScoreLabel;
import database.DBService;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class GameOverScene {
    public Scene scene;
    private VBox layout;
    private CustomButton Next;
    private ScoreLabel scoreLabel;
    private TextField NameField;


    private Text gameOver;
    private Text Score;
    private Text nameLable;
    Main mainMenu;
    int levelReached;

    public GameOverScene(Main mainMenu, ScoreLabel scoreLabel, int levelReached) {
        this.scoreLabel = scoreLabel;
        this.mainMenu=mainMenu;
        this.levelReached=levelReached;



        Next = new CustomButton("Next");
        Next.setOnAction(event1 -> {
               query(mainMenu,levelReached);
        });

        NameField = new TextField();

        NameField.setMaxSize(200, 100);
        NameField.setFont(Font.font("Informal Roman", FontWeight.BOLD, 30));
        NameField.setStyle("-fx-background-color: #CBC6AF;");


        nameLable = new Text("ENTER YOUR NAME");
        nameLable.setFont(Font.font("Informal Roman", FontWeight.BOLD, 30));
        nameLable.setCache(true);
        nameLable.setFill(Color.web("#FF915B"));


        gameOver = new Text("Game Over");
        gameOver.setFont(Font.font("Chiller", FontWeight.BOLD, 60));
        gameOver.setCache(true);
        gameOver.setFill(Color.RED);
        gameOver.setTranslateY(gameOver.getTranslateY() - 20);


        Score = new Text("Score:" + String.valueOf(scoreLabel.getScore()));
        Score.setFont(Font.font("Harrington", FontWeight.BOLD, 50));
        Score.setCache(true);
        Score.setFill(Color.web("#C8A780"));
        Score.setTranslateY(Score.getTranslateY() - 20);

        setScene();
    }

    public void setScene() {
        layout = new VBox(20, gameOver, Score, nameLable, NameField, Next);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 800, 600);
        layout.setStyle("-fx-background-color: #000000;");

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                query(mainMenu,levelReached);
            }
        });


    }

    public Scene getScene() {
        return this.scene;
    }


    public void updateDatabase(ScoreBoard sb) {
        //send queries from the data of the given scoreboard object.
        try {
            DBService ds = new DBService();
            ds.updateScoreBoard(sb.getName(), sb.getScore(), sb.getLvlReached());
            ds.dbCon.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void query(Main mainMenu,int levelReached){
        ScoreBoard SB;
        String name = NameField.getText();
        if (!name.isEmpty()) {
            SB = new ScoreBoard(name, String.valueOf(scoreLabel.getScore()), String.valueOf(levelReached));
        } else {
            SB = new ScoreBoard("NameLessWonder", String.valueOf(scoreLabel.getScore()), String.valueOf(levelReached));
        }

        updateDatabase(SB);
        mainMenu.getWindow().setScene(new HighScoreScene(mainMenu).getScene());
    }



}
